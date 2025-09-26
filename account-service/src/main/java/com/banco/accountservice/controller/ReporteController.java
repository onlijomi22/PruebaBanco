package com.banco.accountservice.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banco.accountservice.dto.EstadoCuentaReporteDTO;
import com.banco.accountservice.service.ReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<EstadoCuentaReporteDTO> estadoCuenta(
            @RequestParam String clienteId,
            @RequestParam String desde,
            @RequestParam String hasta
    ) {
        LocalDateTime fechaDesde = LocalDateTime.parse(desde);
        LocalDateTime fechaHasta = LocalDateTime.parse(hasta);

        EstadoCuentaReporteDTO reporte = reporteService.generarReporte(clienteId, fechaDesde, fechaHasta);
        return ResponseEntity.ok(reporte);
    }
}
