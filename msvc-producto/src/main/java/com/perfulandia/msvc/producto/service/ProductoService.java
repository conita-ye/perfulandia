package com.perfulandia.msvc.producto.service;


import com.perfulandia.msvc.producto.model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> findAll();
    Producto findById (Long id);
    Producto save (Producto producto);
}
