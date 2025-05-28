package com.perfulandia.msvc.inventario.clients;


import com.perfulandia.msvc.inventario.model.entities.Inventario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-producto", url = "Localhost:8080/api/v1/inventarios")
public interface ProductoClientRest {

    @GetMapping("/{id}")
    Inventario findById(@PathVariable Long id);
}
