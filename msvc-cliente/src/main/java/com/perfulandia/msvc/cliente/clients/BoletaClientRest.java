package com.perfulandia.msvc.cliente.clients;

import com.perfulandia.msvc.cliente.model.Boleta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-boleta", url = "localhost:8001/api/v1/boletas")
public interface BoletaClientRest {
    @GetMapping("/{id}")
    Boleta findById (@PathVariable Long id);
}
