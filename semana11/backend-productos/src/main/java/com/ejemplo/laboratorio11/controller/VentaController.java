package com.ejemplo.laboratorio11.controller;

import com.ejemplo.laboratorio11.dto.VentaRequest;
import com.ejemplo.laboratorio11.model.Venta;
import com.ejemplo.laboratorio11.service.VentaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> listar() {
        return ventaService.listarVentas();
    }

    @PostMapping
    public ResponseEntity<Venta> registrar(
            @RequestBody VentaRequest request) {

        Venta venta = ventaService.registrarVenta(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(venta);
    }
}
