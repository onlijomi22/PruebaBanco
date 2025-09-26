package com.banco.client_service;

import com.banco.client_service.model.Cliente;
import com.banco.client_service.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
class ClienteUnitariaTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void guardarCliente() {
        Cliente c = new Cliente();
        
        c.setClienteId("C001");
        c.setNombre("Juan");
        c.setTelefono("7777777");
        c.setIdentificacion("454545554");
        c.setDireccion("Amazonas");
        c.setContrasena("22222");
        c.setGenero("Masculino");
        clienteRepository.save(c);

        assertEquals(1, clienteRepository.findAll().size());
        
        
    }
}
