package com.ejemplo.laboratorio11.service;

import com.ejemplo.laboratorio11.dto.VentaRequest;
import com.ejemplo.laboratorio11.model.Producto;
import com.ejemplo.laboratorio11.model.Venta;
import com.ejemplo.laboratorio11.repository.ProductoRepository;
import com.ejemplo.laboratorio11.repository.VentaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaService {

    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;

    public VentaService(
            ProductoRepository productoRepository,
            VentaRepository ventaRepository) {
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Transactional
    public Venta registrarVenta(VentaRequest request) {

        if (request.getCantidad() == null || request.getCantidad() <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero");
        }

        Producto producto = productoRepository
                .findById(request.getProductoId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Producto no encontrado"));

        if (producto.getStock() < request.getCantidad()) {
            throw new IllegalArgumentException(
                    "Stock insuficiente para realizar la venta");
        }

        producto.setStock(
                producto.getStock() - request.getCantidad());
        productoRepository.saveAndFlush(producto);

        Venta venta = new Venta();
        venta.setProducto(producto);
        venta.setCantidad(request.getCantidad());
        venta.setPrecioUnitario(producto.getPrecio());
        venta.setTotal(
                producto.getPrecio() * request.getCantidad());
        venta.setFecha(LocalDateTime.now());

        Venta ventaGuardada = ventaRepository.saveAndFlush(venta);

        if (Boolean.TRUE.equals(request.getSimularError())) {
            throw new RuntimeException(
                    "Error simulado: Spring debe ejecutar ROLLBACK");
        }

        return ventaGuardada;
    }
}
