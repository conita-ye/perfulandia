package com.perfulandia.msvc.producto.service;

import com.perfulandia.msvc.producto.exceptions.ProductoException;
import com.perfulandia.msvc.producto.model.entities.Producto;
import com.perfulandia.msvc.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List <Producto> findAll (){
        return this.productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return this.productoRepository.findById(id).orElseThrow(
                () -> new ProductoException ("La boleta con id"+id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public Producto save (Producto producto){
        return this.productoRepository.save(producto);
    }
}






