package com.banco.client_service.controller;

import com.banco.client_service.model.Cliente;
import com.banco.client_service.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listar();
    }
    
    @GetMapping("/id/{clienteId}")
    public ResponseEntity<Cliente> obtenerPorClienteId(@PathVariable String clienteId) {
        return ResponseEntity.ok(clienteService.buscarPorClienteId(clienteId));
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.crear(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable String id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizar(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
