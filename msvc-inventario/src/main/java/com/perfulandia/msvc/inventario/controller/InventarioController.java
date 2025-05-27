package com.perfulandia.msvc.inventario.controller;

import com.perfulandia.msvc.inventario.model.Inventario;
import com.perfulandia.msvc.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventarios")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(inventarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(inventarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Inventario> save(@RequestBody @Validated Inventario inventario){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioService.save(inventario));
    }
}
