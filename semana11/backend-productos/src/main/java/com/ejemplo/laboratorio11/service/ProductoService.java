package com.ejemplo.laboratorio11.service;

import com.ejemplo.laboratorio11.model.Producto;
import com.ejemplo.laboratorio11.repository.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Page<Producto> listarPaginado(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    public Page<Producto> buscarPorNombre(String nombre, Pageable pageable) {
        return productoRepository.findByNombreContainingIgnoreCase(
                nombre, pageable);
    }

    public Page<Producto> buscarAvanzado(
            String nombre,
            Double precioMin,
            Double precioMax,
            Integer stockMin,
            Pageable pageable) {

        return productoRepository.buscarAvanzado(
                nombre,
                precioMin,
                precioMax,
                stockMin,
                pageable);
    }
}
