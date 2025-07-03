package com.perfulandia.msvc.producto.service;

import com.perfulandia.msvc.producto.clients.InventarioClientRest;
import com.perfulandia.msvc.producto.exceptions.ProductoException;
import com.perfulandia.msvc.producto.model.Inventario;
import com.perfulandia.msvc.producto.model.entities.Producto;
import com.perfulandia.msvc.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
        @Autowired
        private ProductoRepository productoRepository;
        @Autowired
        private InventarioClientRest inventarioClientRest;

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
//            try{
//                Inventario inventario = this.inventarioClientRest.findById(producto.getIdInventario());
//            }catch (FeignException exception){
//                throw new ProductoException("El inventario con id "+producto.getIdInventario() + " no se encuentra en la base de datos"
//                        + " por ende no se puede generar el nexo de relación");
//            }
            return productoRepository.save(producto);
        }
        @Override
        public Producto actualizarProducto(Long id, Producto producto) {
            Producto productoBuscado = this.findById(id);
            productoBuscado.setNombreProducto(producto.getNombreProducto());
            productoBuscado.setFechaElaboracion(producto.getFechaElaboracion());
            productoBuscado.setFechaVencimiento(producto.getFechaVencimiento());
            productoBuscado.setCategoria(producto.getCategoria());
            productoBuscado.setStock(producto.getStock());
            productoBuscado.setPrecio(producto.getPrecio());

            return this.productoRepository.save(productoBuscado);
        }


        @Override
            public void eliminarProducto(Long id) {
                productoRepository.deleteById(id);
            }
}






