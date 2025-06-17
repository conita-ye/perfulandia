package com.perfulandia.msvc.boleta.controller;

import com.perfulandia.msvc.boleta.model.entities.Boleta;
import com.perfulandia.msvc.boleta.service.BoletaService;
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
@RequestMapping("api/v1/boletas")
@Validated
@Tag(name = "Boletas", description = "Esta sección contiene los CRUD de boleta")
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @GetMapping
    @Operation(
            summary = "Listar todas las boletas", description = "Este metodo debe listar las boletas")

    @ApiResponse(
            responseCode = "200", description = "Se listaron los productos")
    public List<Boleta> listarBoleta(){
        return boletaService.listarBoleta();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> findById (@PathVariable Long id) {
        Optional <Boleta> boleta = Optional.ofNullable(boletaService.findById(id));
        return boleta.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Boleta> save (@RequestBody @Valid Boleta boleta) {
        Boleta nuevaBoleta = boletaService.save(boleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(boletaService.save(boleta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boleta> actualizarBoleta (@PathVariable Long id, @Valid @RequestBody Boleta boleta){
        Optional<Boleta> boletaExistente = Optional.ofNullable(boletaService.findById(id));

        if (boletaExistente.isPresent()) {
            Boleta actualizada = boletaExistente.get();

            actualizada.setIdBoleta(boleta.getIdBoleta());
            actualizada.setFechaEmision(boleta.getFechaEmision());
            actualizada.setNombreCliente(boleta.getNombreCliente());

            return ResponseEntity.ok(boletaService.save(actualizada));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBoleta (@PathVariable Long id){
        Optional<Boleta> boleta = Optional.ofNullable(boletaService.findById(id));

        if (boleta.isPresent()){
            boletaService.eliminarBoleta(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
