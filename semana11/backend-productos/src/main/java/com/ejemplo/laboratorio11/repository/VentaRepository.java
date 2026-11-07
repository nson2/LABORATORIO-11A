package com.ejemplo.laboratorio11.repository;

import com.ejemplo.laboratorio11.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
