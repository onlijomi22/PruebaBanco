package com.banco.accountservice.controller;

import com.banco.accountservice.dto.CuentaDTO;
import com.banco.accountservice.model.Cuenta;
import com.banco.accountservice.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }
    
    @GetMapping
    public ResponseEntity<List<CuentaDTO>> listarCuentas() {
        List<Cuenta> cuentas = cuentaService.listarCuentas();
        List<CuentaDTO> dtos = cuentas.stream()
                                      .map(cuentaService::mapToDTO)
                                      .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
        
    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> getCuenta(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.buscarPorId(id);
        return ResponseEntity.ok(cuentaService.mapToDTO(cuenta));
    }

    //busca por número de cuenta
    @GetMapping("/numero/{numeroCuenta}")
    public ResponseEntity<Cuenta> obtenerPorNumero(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(cuentaService.buscarPorNumeroCuenta(numeroCuenta));
    }

    //crear nueva cuenta
    @PostMapping
    public ResponseEntity<Cuenta> crear(@RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.crearCuenta(cuenta));
    }

    //actualizar cuenta
    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizar(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.actualizarCuenta(id, cuenta));
    }

    //eliminar cuenta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
