package com.banco.accountservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banco.accountservice.dto.CuentaReporteDTO;
import com.banco.accountservice.dto.EstadoCuentaReporteDTO;
import com.banco.accountservice.dto.MovimientoReporteDTO;
import com.banco.accountservice.model.Cuenta;
import com.banco.accountservice.model.Movimiento;
import com.banco.accountservice.repository.CuentaRepository;
import com.banco.accountservice.repository.MovimientoRepository;

@Service
public class ReporteService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public ReporteService(CuentaRepository cuentaRepository,
                          MovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public EstadoCuentaReporteDTO generarReporte(String clienteId, LocalDateTime desde, LocalDateTime hasta) {
        EstadoCuentaReporteDTO reporte = new EstadoCuentaReporteDTO();
        reporte.setClienteId(clienteId);

        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        List<CuentaReporteDTO> cuentasReporte = cuentas.stream().map(cuenta -> {
            CuentaReporteDTO cuentaDTO = new CuentaReporteDTO();
            cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaDTO.setSaldoActual(cuenta.getSaldoActual());

            List<Movimiento> movimientos = movimientoRepository
                    .findByCuentaClienteIdAndFechaBetween(clienteId, desde, hasta);

            List<MovimientoReporteDTO> movimientosDTO = movimientos.stream().map(mov -> {
                MovimientoReporteDTO mDTO = new MovimientoReporteDTO();
                mDTO.setFecha(mov.getFecha());
                mDTO.setTipoMovimiento(mov.getTipoMovimiento());
                mDTO.setValor(mov.getValor());
                mDTO.setSaldoDisponible(mov.getSaldoDisponible());
                return mDTO;
            }).toList();

            cuentaDTO.setMovimientos(movimientosDTO);
            return cuentaDTO;
        }).toList();

        reporte.setCuentas(cuentasReporte);
        return reporte;
    }
}
