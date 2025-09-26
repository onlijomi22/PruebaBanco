package com.banco.accountservice.dto;

import java.time.LocalDateTime;

public class MovimientoReporteDTO {
	
	private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldoDisponible;
    
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(Double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	
    
    
    
}
