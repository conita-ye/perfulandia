package com.perfulandia.msvc.carrocompras.service;

import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;

import java.util.List;

public interface CarroComprasService {
    List<CarroCompras> listarCarros();
    CarroCompras findById(Long id);
    CarroCompras save(CarroCompras carroCompras);
    void eliminarCarro(Long id);
}
