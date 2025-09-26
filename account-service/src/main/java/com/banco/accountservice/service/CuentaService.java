package com.banco.accountservice.service;

import com.banco.accountservice.model.Cuenta;
import com.banco.accountservice.model.Movimiento;
import com.banco.accountservice.repository.CuentaRepository;
import com.banco.accountservice.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.banco.accountservice.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    private final RestTemplate restTemplate;

    public CuentaService(CuentaRepository cuentaRepository, RestTemplate restTemplate) {
        this.cuentaRepository = cuentaRepository;
        this.restTemplate = restTemplate;
    }

    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta buscarPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + id));
    }

    public Cuenta buscarPorNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con número: " + numeroCuenta));
    }

    @Transactional
    public Cuenta crearCuenta(Cuenta cuenta) {
        if (cuentaRepository.existsByNumeroCuenta(cuenta.getNumeroCuenta())) {
            throw new IllegalArgumentException("Ya existe una cuenta con este número: " + cuenta.getNumeroCuenta());
        }

        if (!existeCliente(cuenta.getClienteId())) {
            throw new RuntimeException("Cliente no existe, no se puede crear cuenta");
        }

        cuenta.setSaldoActual(cuenta.getSaldoInicial());
        return cuentaRepository.save(cuenta);
    }

    @Transactional
    public Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada) {
        Cuenta cuenta = buscarPorId(id);
        cuenta.setTipoCuenta(cuentaActualizada.getTipoCuenta());
        cuenta.setEstado(cuentaActualizada.getEstado());
        
        return cuentaRepository.save(cuenta);
    }

    @Transactional
    public void eliminarCuenta(Long id) {
        Cuenta cuenta = buscarPorId(id);
        cuentaRepository.delete(cuenta);
    }
    
    public boolean existeCliente(String clienteId) {
        try {          
            ResponseEntity<ClienteDTO> response = restTemplate.getForEntity(
            	    "http://cliente-service:8081/clientes/id/" + clienteId,
            	    ClienteDTO.class
            	);            
            
            return response.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
    
    public CuentaDTO mapToDTO(Cuenta cuenta) {
        CuentaDTO dto = new CuentaDTO();
        dto.setId(cuenta.getId());
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(cuenta.getTipoCuenta());
        dto.setSaldoInicial(cuenta.getSaldoInicial());
        dto.setSaldoActual(cuenta.getSaldoActual());
        dto.setEstado(cuenta.getEstado());
        dto.setClienteId(cuenta.getClienteId());

        if (cuenta.getMovimientos() != null) {
            dto.setMovimientos(
                cuenta.getMovimientos().stream()
                      .map(this::mapMovimientoToDTO)
                      .collect(Collectors.toList())
            );
        }
        return dto;
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


