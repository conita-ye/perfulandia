package com.perfulandia.msvc.producto.service;

import com.perfulandia.msvc.producto.model.entities.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> listarProducto();       // en lugar de findAll()
    Producto findById(Long id);
    Producto guardarProducto(Producto producto); // en lugar de save()+
    Producto actualizarProducto(Long id, Producto producto);
    void eliminarProducto(Long id);
}
