package com.banco.client_service.service;

import com.banco.client_service.model.Cliente;
import com.banco.client_service.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import com.banco.client_service.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorClienteId(String clienteId) {
        return clienteRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + clienteId));
    }
    
    public Cliente crear(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizar(String id, Cliente clienteActualizado) {
        Cliente cliente = buscarPorClienteId(id);
        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setDireccion(clienteActualizado.getDireccion());
        cliente.setTelefono(clienteActualizado.getTelefono());       
        return clienteRepository.save(cliente);
    }
       
    public void eliminar(String id) {
        Cliente cliente = buscarPorClienteId(id);
        clienteRepository.delete(cliente);
    }
}
