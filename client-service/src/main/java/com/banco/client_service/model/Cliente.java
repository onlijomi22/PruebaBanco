package com.banco.client_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona {

    @Column(unique = true, nullable = false)
    private String clienteId;

    private String contrasena;

    private Boolean activo = true;

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

    
    
    
}
