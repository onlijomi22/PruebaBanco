package com.banco.accountservice.dto;

import java.util.List;

public class CuentaDTO {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Double saldoActual;
    private Boolean estado;
    private String clienteId;
    private List<MovimientoDTO> movimientos; 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public Double getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public Double getSaldoActual() {
		return saldoActual;
	}
	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public String getClienteId() {
		return clienteId;
	}
	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
	public List<MovimientoDTO> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<MovimientoDTO> movimientos) {
		this.movimientos = movimientos;
	}


    
    
}
