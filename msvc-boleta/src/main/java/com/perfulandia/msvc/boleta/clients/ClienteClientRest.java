package com.perfulandia.msvc.boleta.clients;

import com.perfulandia.msvc.boleta.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cliente", url = "localhost:8003/api/v1/clientes")
public interface ClienteClientRest {
    @GetMapping("/{id}")
    Cliente findById (@PathVariable Long id);
}
