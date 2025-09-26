package com.banco.accountservice.dto;

import java.time.LocalDateTime;

public class MovimientoDTO {
    private String tipoMovimiento; 
    private Double valor;
    private Long cuentaId;    
    private Long id;
    private Double saldoDisponible;
    private LocalDateTime fecha;
    
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
	public Long getCuentaId() {
		return cuentaId;
	}
	public void setCuentaId(Long cuentaId) {
		this.cuentaId = cuentaId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(Double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	} 
	
	

}
