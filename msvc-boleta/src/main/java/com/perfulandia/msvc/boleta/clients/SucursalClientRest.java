package com.perfulandia.msvc.boleta.clients;

import com.perfulandia.msvc.boleta.model.Sucursal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-sucursal", url = "localhost:8006/api/v1/sucursales")
public interface SucursalClientRest {
    @GetMapping("/{id}")
    Sucursal findById (@PathVariable Long id);
}
