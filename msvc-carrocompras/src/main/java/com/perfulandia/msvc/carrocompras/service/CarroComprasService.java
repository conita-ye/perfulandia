package com.perfulandia.msvc.carrocompras.service;

import com.perfulandia.msvc.carrocompras.clients.ProductoClientRest;
import com.perfulandia.msvc.carrocompras.clients.ClienteClientRest;
import com.perfulandia.msvc.carrocompras.exceptions.CarroComprasException;
import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;
import com.perfulandia.msvc.carrocompras.model.entities.Producto;
import com.perfulandia.msvc.carrocompras.model.Cliente;
import com.perfulandia.msvc.carrocompras.repository.CarroComprasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import feign.FeignException;


import java.util.List;

@Service
public class CarrocomprasService implements CarrocomprasService {

    @Autowired
    private CarroComprasRepository carroComprasRepository;
    @Autowired
    private ProductoClientRest productoClientRest;
    @Autowired
    private ClienteClientRest clienteClientRest;

    @Override
    public List<CarroCompras> findAll() {
        return carroComprasRepository.findAll();
    }

    @Override
    public CarroCompras findById(Long id) {
        return carroComprasRepository.findById(id).orElseThrow(
                () -> new CarroComprasException("El carro con id " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    @Transactional
    public CarroCompras save(CarroCompras carroCompras) {
        // Verificación de Producto
        try {
            Producto producto = productoClientRest.findById(carroCompras.getIdProducto());
            if (producto == null) {
                throw new CarroComprasException("El producto con id " + carroCompras.getIdProducto() + " no existe.");
            }
        } catch (FeignException exception) {
            throw new CarroComprasException("El producto con id " + carroCompras.getIdProducto() + " no se encuentra.");
        }

        // Verificación de Cliente
        try {
            Cliente cliente = clienteClientRest.findById(carroCompras.getIdCliente());
            if (cliente == null) {
                throw new CarroComprasException("El cliente con id " + carroCompras.getIdCliente() + " no existe.");
            }
        } catch (FeignException exception) {
            throw new CarroComprasException("El cliente con id " + carroCompras.getIdCliente() + " no se encuentra.");
        }

        // Guardar carro de compras
        return carroComprasRepository.save(carroCompras);
    }

    @Override
    public void deleteById(Long id) {
        carroComprasRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CarroCompras updateById(Long id, CarroCompras carroCompras) {
        // Verificar si el carro de compras existe
        CarroCompras existingCarro = carroComprasRepository.findById(id).orElseThrow(
                () -> new CarroComprasException("El carro con id " + id + " no existe para actualizar")
        );

        // Actualizar los campos según los valores de carroCompras
        existingCarro.setIdProducto(carroCompras.getIdProducto());
        existingCarro.setCantidadProducto(carroCompras.getCantidadProducto());
        existingCarro.setPrecioUnitario(carroCompras.getPrecioUnitario());
        existingCarro.setIdCliente(carroCompras.getIdCliente());
        existingCarro.setIdBoleta(carroCompras.getIdBoleta());

        // Guardar el carro actualizado
        return carroComprasRepository.save(existingCarro);
    }
}
