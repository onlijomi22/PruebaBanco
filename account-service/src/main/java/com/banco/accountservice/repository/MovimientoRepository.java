package com.banco.accountservice.repository;

import com.banco.accountservice.model.Movimiento;
import com.banco.accountservice.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuenta(Cuenta cuenta);  
    List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDateTime desde, LocalDateTime hasta);
    List<Movimiento> findByCuentaClienteIdAndFechaBetween(String clienteId, LocalDateTime desde, LocalDateTime hasta);

}
