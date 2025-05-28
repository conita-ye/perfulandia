package com.perfulandia.msvc.inventario.clients;


import com.perfulandia.msvc.inventario.model.Sucursal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-sucursal",url = "Localhost:8080/api/v1/sucursales")
public interface SucursalClientRest {

    @GetMapping("/{id}")
   Sucursal findById (@PathVariable long id);
}

