package com.banco.client_service;

import com.banco.client_service.model.Cliente;
import com.banco.client_service.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ClienteIntegracionTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    void testCrearYListarCliente() {
        Cliente cliente = new Cliente();
        cliente.setClienteId("C002");
        cliente.setNombre("Ana Gomez");
        cliente.setIdentificacion("454545554");
        cliente.setTelefono("0988888888");
        cliente.setDireccion("NNUU");
        cliente.setActivo(true);

        Cliente saved = clienteService.crear(cliente);

        assertNotNull(saved.getId());

        Cliente found = clienteService.listar()
                .stream()
                .filter(c -> c.getClienteId().equals("C002"))
                .findFirst()
                .orElse(null);

        assertNotNull(found);
        assertEquals("Ana Gomez", found.getNombre());
    }
}
