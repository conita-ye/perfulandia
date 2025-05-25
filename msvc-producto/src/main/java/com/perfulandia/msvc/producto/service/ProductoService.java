package com.perfulandia.msvc.producto.service;

import com.perfulandia.msvc.producto.model.Producto;
import com.perfulandia.msvc.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {

    @Autowired
   private ProductoRepository productoRepository;

    public List<Producto> listarProducto();
    public Producto findById(Long id);
    public Producto guardarProducto(Producto producto);
    public void eliminarProducto(Long id);

}
