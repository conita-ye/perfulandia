package com.perfulandia.msvc.carrocompras.service;


import com.perfulandia.msvc.carrocompras.clients.BoletaClientRest;
import com.perfulandia.msvc.carrocompras.clients.ClienteClientRest;
import com.perfulandia.msvc.carrocompras.exceptions.CarroComprasException;
import com.perfulandia.msvc.carrocompras.model.Boleta;
import com.perfulandia.msvc.carrocompras.model.Cliente;
import com.perfulandia.msvc.carrocompras.repository.CarroComprasRepository;
import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroComprasServiceImpl implements CarroComprasService {

    @Autowired
    private CarroComprasRepository carroComprasRepository;
    @Autowired
    private BoletaClientRest boletaClientRest;
    @Autowired
    private ClienteClientRest clienteClientRest;

    @Override
    public List<CarroCompras> findAll () {
        return this.carroComprasRepository.findAll();
    }

    @Override
    public CarroCompras findById(Long id) {
        return this.carroComprasRepository.findById(id).orElseThrow (
                () -> new CarroComprasException("El carro con id"+id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public CarroCompras save (CarroCompras carroCompras){
        try {
            Cliente cliente = this.clienteClientRest.findById(carroCompras.getIdCliente());
        }
        catch (FeignException exception){
            throw new CarroComprasException("El cliente con id"+carroCompras.getIdCliente()+" no se encuentra en la base de datos"
                    + " por ende no puedo generar el nexo de relación");
        }

        try {
            Boleta boleta = this.boletaClientRest.findById(carroCompras.getIdBoleta());
        }
        catch (FeignException exception){
            throw new CarroComprasException("La boleta con id"+carroCompras.getIdBoleta()+ "no se encuentra en la base de datos"
                    + "por ende no puedo generar el nexo de relación");
        }

        return this.carroComprasRepository.save (carroCompras);
    }

}
