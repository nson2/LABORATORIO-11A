package com.ejemplo.laboratorio11.controller;

import com.ejemplo.laboratorio11.model.Producto;
import com.ejemplo.laboratorio11.service.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/pagina")
    public Page<Producto> listarPaginado(
            @PageableDefault(size = 5, sort = "id")
            Pageable pageable) {

        return productoService.listarPaginado(pageable);
    }

    @GetMapping("/pagina/buscar")
    public Page<Producto> buscar(
            @RequestParam String nombre,
            @PageableDefault(size = 5, sort = "id")
            Pageable pageable) {

        return productoService.buscarPorNombre(nombre, pageable);
    }
}