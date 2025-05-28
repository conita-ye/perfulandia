package com.perfulandia.msvc.producto.clients;

import com.perfulandia.msvc.producto.model.Inventario;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-inventario", url = "localhost:8002/api/v1/inventarios")
public interface InventarioClientRest {
    @GetMapping("/{id}")
    Inventario findById(@PathVariable Long id);
}
