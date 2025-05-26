package com.perfulandia.msvc.producto.service;

import com.perfulandia.msvc.producto.model.Producto;
import com.perfulandia.msvc.producto.repository.ProductoRepository;
import java.util.List;

public interface ProductoService {
    public List<Producto> listarProducto();

    List<Producto> finAll();

    public Producto findById(Long id) throws Throwable;
    public Producto guardarProducto(Producto producto);
    public void eliminarProducto(Long id);

    Producto save(Producto producto);
}
