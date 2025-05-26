package com.perfulandia.msvc.producto.service;

import com.perfulandia.msvc.producto.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.perfulandia.msvc.producto.repository.ProductoRepository;
import com.perfulandia.msvc.producto.exceptions.ProductoException;
import java.util.List;


@Service
public abstract class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List finAll() {
        return this.productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) throws Throwable {
        return (Producto) this.productoRepository.findById(id).orElseThrow(
                () -> new ProductoException("La boleta con id"+id+" no se encuentra en la base de datos")
        );
    }
    @Override
    public Producto save(Producto producto) {
        return (Producto) this.productoRepository.save(producto);
    }
}






