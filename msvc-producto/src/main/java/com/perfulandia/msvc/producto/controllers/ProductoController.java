package com.perfulandia.msvc.producto.controllers;

import com.perfulandia.msvc.producto.model.Producto;
import com.perfulandia.msvc.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.perfulandia.msvc.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productos")
@Validated
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarProducto(){
        return productoService.listarProducto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto){
        Producto nuevoProducto = guardarProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto){
        return productoService.guardarProducto(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Optional<Producto> productoExistente = Optional.ofNullable(productoService.findById(id));

        if (productoExistente.isPresent()) {
            Producto actualizado = productoExistente.get();

            actualizado.setNombreProducto(producto.getNombreProducto());
            actualizado.setFechaElaboracion(producto.getFechaElaboracion());
            actualizado.setFechaVencimiento(producto.getFechaVencimiento());
            actualizado.setCatergoria(producto.getCatergoria());
            actualizado.setStock(producto.getStock());
            actualizado.setPrecio(producto.getPrecio());

            return ResponseEntity.ok(productoService.guardarProducto(actualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        if(Boolean.parseBoolean(findById(id).toString())){
            eliminarProducto(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}