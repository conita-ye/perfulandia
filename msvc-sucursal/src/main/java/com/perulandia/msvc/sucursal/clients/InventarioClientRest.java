package com.perulandia.msvc.sucursal.clients;

import com.perulandia.msvc.sucursal.model.Inventario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-inventario", url = "Localhost:8080/api/v1/inventarios")
public interface InventarioClientRest {

    @GetMapping("/{id}")
    Inventario findById(@PathVariable long id);
}
