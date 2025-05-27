package com.perfulandia.msvc.carrocompras.service;


import com.perfulandia.msvc.carrocompras.exceptions.CarroComprasException;
import com.perfulandia.msvc.carrocompras.model.CarroCompras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroComprasServiceImpl {
    @Autowired
    private CarroComprasService carroComprasRepository;

    @Override
    public List<CarroCompras> findAll () {
        return this.carroComprasRepository.findAll();
    }

    @Override
    public CarroCompras findById(Long id) {
        return this.carroComprasRepository.findById(id).orElseThrow (
                () -> new CarroComprasException("El carro con id"+id+" no se encuentra en la base de datos");
        );
    }

}
