package com.banco.accountservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(name = "tipo_movimiento", nullable = false)
    private String tipoMovimiento; // Ej: Deposito, Retiro

    @Column(nullable = false)
    private Double valor;

    @Column(name = "saldo_disponible", nullable = false)
    private Double saldoDisponible;

    // Relación con cuenta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
