package com.perfulandia.msvc.boleta.clients;

import com.perfulandia.msvc.boleta.model.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-producto", url = "localhost:8005/api/v1/productos")
public interface ProductoClientRest {
    @GetMapping("/{id}")
    Producto findById (@PathVariable Long id);
}
