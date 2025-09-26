package com.banco.accountservice.dto;

import java.util.List;

public class EstadoCuentaReporteDTO {

	private String clienteId;
    private List<CuentaReporteDTO> cuentas;
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public List<CuentaReporteDTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(List<CuentaReporteDTO> cuentas) {
		this.cuentas = cuentas;
	}
	
	
}
