package com.perfulandia.msvc.carrocompras.service;

import com.perfulandia.msvc.carrocompras.exceptions.CarroComprasException;
import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;
import com.perfulandia.msvc.carrocompras.repository.CarroComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroComprasServiceImpl implements CarroComprasService {

    @Autowired
    private CarroComprasRepository carroComprasRepository;

    @Override
    public List<CarroCompras> listarCarros() {
        return carroComprasRepository.findAll();
    }

    @Override
    public CarroCompras findById(Long id) {
        return carroComprasRepository.findById(id)
                .orElseThrow(() -> new CarroComprasException("El carro con ID " + id + " no existe."));
    }

    @Override
    public CarroCompras save(CarroCompras carroCompras) {
        return carroComprasRepository.save(carroCompras);
    }

    @Override
    public void eliminarCarro(Long id) {
        carroComprasRepository.deleteById(id);
    }
}
