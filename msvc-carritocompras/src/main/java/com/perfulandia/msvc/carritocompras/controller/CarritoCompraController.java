package com.perfulandia.msvc.carritocompras.controller;


import com.perfulandia.msvc.carritocompras.model.CarritoCompra;
import com.perfulandia.msvc.carritocompras.service.CarritoCompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carritoCompras")
@Validated
public class CarritoCompraController {
    @Autowired
    private CarritoCompraService carritoCompraService;

    @GetMapping
    public ResponseEntity<List<CarritoCompra>> findAll(){
        return ResponseEntity.status(HttpStatusCode.OK).body(carritoCompraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoCompra> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(carritoCompraService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CarritoCompra> save(@RequestBody @Valid CarritoCompra carritoCompra){
        return ResponseEntity.status(HttpStatus.CREATED).body(carritoCompraService.save(carritoCompra));
    }


}

