package com.macaesp.msvc.carritocompras.servicio;

import com.macaesp.msvc.carritocompras.model.CarritoCompra;
import com.macaesp.msvc.carritocompras.repository.CarritoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoCompraService {

    private final CarritoCompraRepository carritoCompraRepository;

    @Autowired
    public CarritoCompraService(CarritoCompraRepository carritoCompraRepository) {
        this.carritoCompraRepository = carritoCompraRepository;
    }

    // Obtener todos los carritos de compras
    public List<CarritoCompra> obtenerTodos() {
        return carritoCompraRepository.findAll();
    }

    // Obtener carrito de compras por ID
    public Optional<CarritoCompra> obtenerPorId(Long id) {
        return carritoCompraRepository.findById(id);
    }

    // Guardar un nuevo carrito de compras
    public CarritoCompra guardar(CarritoCompra carritoCompra) {
        return carritoCompraRepository.save(carritoCompra);
    }

    // Eliminar un carrito de compras por ID
    public void eliminar(Long id) {
        carritoCompraRepository.deleteById(id);
    }
}
