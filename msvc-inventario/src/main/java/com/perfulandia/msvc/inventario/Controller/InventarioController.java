package com.perfulandia.msvc.inventario.Controller;

import com.perfulandia.msvc.inventario.model.Inventario;
import com.perfulandia.msvc.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
@Validated

public class InventarioService {
    @Autowired
    private InvtarioService invtarioService;

    @GetMapping
    public ResponseEntity<List<inventario>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(invtarioService.findAll());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<inventario> findById (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(inventarioService.findByid(id));
    }

    @PostMapping
    public ResponseEntity<inventario> save(@Validated @RequestBody Inventario inventario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(invtarioService.save(inventario));
    }
}

public class InventarioController {
}
