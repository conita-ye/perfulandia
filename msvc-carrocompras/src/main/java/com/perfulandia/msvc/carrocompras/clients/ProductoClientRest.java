package com.perfulandia.msvc.carrocompras.clients;


import com.perfulandia.msvc.carrocompras.model.entities.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-productos", url = "http://localhost:8005/api/v2/productos")
public interface ProductoClientRest {

    @GetMapping("/api/v2/productos/{id}")
    Producto findById(@PathVariable("id")Long id);
}
