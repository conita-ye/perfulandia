package com.perfulandia.msvc.boleta.clients;

import com.perfulandia.msvc.boleta.model.CarroCompras;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-carrocompras", url = "localhost:8002/api/v1/carrocompras")
public interface CarroComprasClientRest {
    @GetMapping("/{id}")
    CarroCompras findById (@PathVariable Long id);
}
