package com.perfulandia.msvc.carrocompras.clients;

import com.perfulandia.msvc.carrocompras.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "msvc-cliente", url = "localhost:8080/api/v1/clientes")
public interface ClienteClientRest {
    @GetMapping ("/{id}")
    Cliente findById (@PathVariable Long id);
}
