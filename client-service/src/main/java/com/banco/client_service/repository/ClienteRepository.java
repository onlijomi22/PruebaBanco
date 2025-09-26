package com.banco.client_service.repository;

import com.banco.client_service.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByClienteId(String clienteId);
}
