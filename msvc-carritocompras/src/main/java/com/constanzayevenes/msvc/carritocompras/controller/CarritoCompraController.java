package com.constanzayevenes.msvc.carritocompras.controller;

import com.constanzayevenes.msvc.carritocompras.model.CarritoCompra;
import com.constanzayevenes.msvc.carritocompras.service.CarritoCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carritoCompra")
public class CarritoCompraController {

    private final CarritoCompraService carritoCompraService;

    @Autowired
    public CarritoCompraController(CarritoCompraService carritoCompraService) {
        this.carritoCompraService = carritoCompraService;
    }

    // Obtener todos los carritos de compras
    @GetMapping
    public List<CarritoCompra> obtenerTodos() {
        return carritoCompraService.obtenerTodos();
    }

    // Obtener carrito de compras por ID
    @GetMapping("/{id}")
    public ResponseEntity<CarritoCompra> obtenerPorId(@PathVariable Long id) {
        Optional<CarritoCompra> carritoCompra = carritoCompraService.obtenerPorId(id);
        return carritoCompra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo carrito de compras
    @PostMapping
    public ResponseEntity<CarritoCompra> guardar(@RequestBody CarritoCompra carritoCompra) {
        CarritoCompra nuevoCarrito = carritoCompraService.guardar(carritoCompra);
        return new ResponseEntity<>(nuevoCarrito, HttpStatus.CREATED);
    }

    // Eliminar un carrito de compras por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        carritoCompraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

