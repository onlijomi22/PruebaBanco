package com.banco.accountservice.dto;

public class ClienteDTO {
    private Long id;
    private String clienteId;
    private String nombre;
    private Boolean activo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

    
}
