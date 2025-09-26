package com.banco.accountservice.service;


import com.banco.accountservice.model.Cuenta;
import com.banco.accountservice.model.Movimiento;
import com.banco.accountservice.repository.CuentaRepository;
import com.banco.accountservice.repository.MovimientoRepository;
import com.banco.accountservice.dto.MovimientoDTO;
import com.banco.accountservice.exception.ResourceNotFoundException;
import com.banco.accountservice.exception.SaldoInsuficienteException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;
    
    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository, KafkaTemplate<String, String> kafkaTemplate) {
		this.movimientoRepository = movimientoRepository;
		this.cuentaRepository = cuentaRepository;
		this.kafkaTemplate = kafkaTemplate;
	}

    public List<Movimiento> listarMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento buscarPorId(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con id: " + id));
    }

    @Transactional
    public Movimiento registrarMovimiento(Long cuentaId, String tipoMovimiento, Double valor) {

        Cuenta cuenta = cuentaRepository.findById(cuentaId)
            .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada: " + cuentaId));
        
        double nuevoSaldo = cuenta.getSaldoActual() + valor;
        if (nuevoSaldo < 0) {
            throw new SaldoInsuficienteException("Saldo no disponible");
        }

        cuenta.setSaldoActual(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setValor(valor);
        movimiento.setSaldoDisponible(nuevoSaldo);
        movimiento.setFecha(LocalDateTime.now());

        return movimientoRepository.save(movimiento);
    }


    public List<Movimiento> movimientosPorCuenta(Long cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + cuentaId));
        return movimientoRepository.findByCuenta(cuenta);
    }

    public List<Movimiento> movimientosPorRangoFechas(Long cuentaId, LocalDateTime desde, LocalDateTime hasta) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + cuentaId));
        return movimientoRepository.findByCuentaAndFechaBetween(cuenta, desde, hasta);
    }
    
    public MovimientoDTO mapMovimientoToDTO(Movimiento m) {
        MovimientoDTO dto = new MovimientoDTO();
        dto.setId(m.getId());
        dto.setFecha(m.getFecha());
        dto.setTipoMovimiento(m.getTipoMovimiento());
        dto.setValor(m.getValor());
        dto.setSaldoDisponible(m.getSaldoDisponible());
        return dto;
    }
    
}
