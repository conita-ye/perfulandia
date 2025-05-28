package com.perfulandia.msvc.producto.service;

import com.perfulandia.msvc.producto.exceptions.ProductoException;
import com.perfulandia.msvc.producto.model.entities.Producto;
import com.perfulandia.msvc.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
        @Autowired
        private ProductoRepository productoRepository;

        @Override
        public List<Producto> listarProducto() {
            return productoRepository.findAll();
        }

        @Override
        public Producto findById(Long id) {
            return productoRepository.findById(id).orElseThrow(
                    () -> new ProductoException("La boleta con id " + id + " no se encuentra en la base de datos"));
        }

        @Override
        public Producto guardarProducto(Producto producto) {
            return productoRepository.save(producto);
        }

        @Override
        public void eliminarProducto(Long id) {
            productoRepository.deleteById(id);
        }
}






