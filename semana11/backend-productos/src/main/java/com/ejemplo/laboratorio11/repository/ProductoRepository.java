package com.ejemplo.laboratorio11.repository;

import com.ejemplo.laboratorio11.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Page<Producto> findByNombreContainingIgnoreCase(
            String nombre,
            Pageable pageable
    );
}