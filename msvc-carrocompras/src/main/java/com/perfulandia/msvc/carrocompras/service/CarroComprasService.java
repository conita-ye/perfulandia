package com.perfulandia.msvc.carrocompras.service;

import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;
import jakarta.validation.Valid;

import java.util.List;

public interface CarroComprasService {
    List<CarroCompras>findAll();
    CarroCompras findById (Long id);
    CarroCompras save (CarroCompras carroCompras);
    void deleteById (Long id);
    CarroCompras updateById (Long id, CarroCompras carroCompras);
}
