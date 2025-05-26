package com.perulandia.msvc.sucursal.Controller;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import com.perulandia.msvc.sucursal.model.Sucursal;
import com.perulandia.msvc.sucursal.Service.SucursalService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<Sucursal> listarSucursal(){
        return sucursalService.listarSucursal();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerIdSucrusal(@PathVariable Long id){
        Optional<Sucursal> sucursal = sucursalService.idSucursal(id);
        if(sucursal.isPresent()){
            return ResponseEntity.ok(sucursal.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Sucursal> crearSucursal(@RequestBody Sucursal sucursal){
        return ResponseEntity.ok(sucursalService.guardarSucursal(sucursal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizarSucursal(@PathVariable Long id, @RequestBody Sucursal sucursal){
        try{
            return ResponseEntity.ok(sucursalService.actualizarSucursal(id, sucursal));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id){
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
