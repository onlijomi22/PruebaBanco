package com.banco.accountservice.dto;

import java.util.List;

public class CuentaReporteDTO {

	private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoActual;
    private List<MovimientoReporteDTO> movimientos;
    
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
	public Double getSaldoActual() {
		return saldoActual;
	}
	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}
	public List<MovimientoReporteDTO> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<MovimientoReporteDTO> movimientos) {
		this.movimientos = movimientos;
	}

	
	
}
