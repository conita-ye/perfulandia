package com.perfulandgia.msvc.carrocompras.controller;

import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;
import com.perfulandia.msvc.carrocompras.service.CarroComprasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carrocompras")
@Validated
@Tag(name = "Carrocompras", description = "CRUD completo de carro de compras")
public class CarroComprasController {

    @Autowired
    private CarroComprasService carroComprasService;

    @GetMapping
    @Operation(
            summary = "Listar productos del carro de compras",
            description = "Devuelve todos los productos añadidos al carro por los usuarios"
    )
    @ApiResponse(responseCode = "200", description = "Lista de productos del carro devuelta correctamente")
    public ResponseEntity<List<CarroCompras>> findAll() {
        return ResponseEntity.ok(carroComprasService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un producto por ID")
    public ResponseEntity<CarroCompras> findById(@PathVariable Long id) {
        return carroComprasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Agregar un producto al carro")
    public ResponseEntity<CarroCompras> save(@Valid @RequestBody CarroCompras carroCompras) {
        CarroCompras nuevo = carroComprasService.save(carroCompras);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto del carro")
    public ResponseEntity<CarroCompras> update(@PathVariable Long id, @Valid @RequestBody CarroCompras carroCompras) {
        return carroComprasService.findById(id).map(existente -> {
            existente.setProductoId(carroCompras.getProductoId());
            existente.setClienteId(carroCompras.getClienteId());
            existente.setCantidad(carroCompras.getCantidad());
            existente.setPrecioUnitario(carroCompras.getPrecioUnitario());
            return ResponseEntity.ok(carroComprasService.save(existente));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto del carro por ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return carroComprasService.findById(id).map(c -> {
            carroComprasService.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}


