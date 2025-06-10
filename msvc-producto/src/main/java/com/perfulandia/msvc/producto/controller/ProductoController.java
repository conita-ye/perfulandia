package com.perfulandia.msvc.producto.controller;

import com.perfulandia.msvc.producto.model.entities.Producto;
import com.perfulandia.msvc.producto.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("api/v1/productos")
@Validated
@Tag(name = "Productos", description = "Esta seccion contiene los CRUD de producto")

public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(
            summary = "Listar todos los productos", description = "Este metodo debe listar los productos")
    @ApiResponse(
            responseCode = "200", description = "Se listaron los productos")
    public List<Producto> listarProducto() {
        return productoService.listarProducto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id) {
        Optional<Producto> producto = Optional.ofNullable(productoService.findById(id));
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@Valid @RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        Optional<Producto> productoExistente = Optional.ofNullable(productoService.findById(id));

        if (productoExistente.isPresent()) {
            Producto actualizado = productoExistente.get();

            actualizado.setNombreProducto(producto.getNombreProducto());
            actualizado.setFechaElaboracion(producto.getFechaElaboracion());
            actualizado.setFechaVencimiento(producto.getFechaVencimiento());
            actualizado.setCategoria(producto.getCategoria());
            actualizado.setStock(producto.getStock());
            actualizado.setPrecio(producto.getPrecio());

            return ResponseEntity.ok(productoService.guardarProducto(actualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        Optional<Producto> producto = Optional.ofNullable(productoService.findById(id));

        if (producto.isPresent()) {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
