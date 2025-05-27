package com.perfulandia.msvc.boleta.controller;

import com.perfulandia.msvc.boleta.model.entities.Boleta;
import com.perfulandia.msvc.boleta.service.BoletaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @GetMapping
    public ResponseEntity<List<Boleta>> findAll () {
        return ResponseEntity.status(HttpStatus.OK).body(boletaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> findById (@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(boletaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Boleta> save (@RequestBody @Valid Boleta boleta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boletaService.save(boleta));
    }

}
