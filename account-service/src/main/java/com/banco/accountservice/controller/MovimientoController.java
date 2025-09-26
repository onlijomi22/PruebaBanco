package com.banco.accountservice.controller;

import com.banco.accountservice.dto.MovimientoDTO;
import com.banco.accountservice.model.Movimiento;
import com.banco.accountservice.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDTO>> listar() {
        List<Movimiento> movimientos = movimientoService.listarMovimientos();
        List<MovimientoDTO> dtos = movimientos.stream()
                                      .map(movimientoService::mapMovimientoToDTO)
                                      .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);    	    	
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.buscarPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<MovimientoDTO> crearMovimiento(@RequestBody MovimientoDTO dto) {

        Movimiento movimiento = movimientoService.registrarMovimiento(dto.getCuentaId(), dto.getTipoMovimiento(), dto.getValor());        
        MovimientoDTO response = movimientoService.mapMovimientoToDTO(movimiento);
        return ResponseEntity.ok(response);
        
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<Movimiento>> movimientosPorCuenta(@PathVariable Long cuentaId) {
        return ResponseEntity.ok(movimientoService.movimientosPorCuenta(cuentaId));
    }

    @GetMapping("/cuenta/{cuentaId}/rango")
    public ResponseEntity<List<Movimiento>> movimientosPorRango(
            @PathVariable Long cuentaId,
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta
    ) {
        LocalDateTime fechaDesde = LocalDateTime.parse(desde);
        LocalDateTime fechaHasta = LocalDateTime.parse(hasta);
        return ResponseEntity.ok(
                movimientoService.movimientosPorRangoFechas(cuentaId, fechaDesde, fechaHasta)
        );
    }
}

