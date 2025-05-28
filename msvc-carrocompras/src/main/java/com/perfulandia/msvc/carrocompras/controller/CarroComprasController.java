package com.perfulandia.msvc.carrocompras.controller;

import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;
import com.perfulandia.msvc.carrocompras.service.CarroComprasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/carroCompras")
@Validated
public class CarroComprasController {

    @Autowired
    private CarroComprasService carroComprasService;


    @GetMapping
    public ResponseEntity<List<CarroCompras>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(carroComprasService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CarroCompras> findById(@PathVariable Long id) {
            return ResponseEntity.status(HttpStatus.OK).body(carroComprasService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CarroCompras> save(@RequestBody @Valid CarroCompras carroCompras) {
        CarroCompras saved = carroComprasService.save(carroCompras);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
