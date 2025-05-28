package com.perfulandia.msvc.inventario.service;


import com.perfulandia.msvc.inventario.clients.ProductoClientRest;
import com.perfulandia.msvc.inventario.clients.SucursalClientRest;
import com.perfulandia.msvc.inventario.exception.InventarioException;
import com.perfulandia.msvc.inventario.model.entities.Inventario;
import com.perfulandia.msvc.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService{
    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private ProductoClientRest productoClientRest;
    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Override
    public List<Inventario>findAll(){
        return this.inventarioRepository.findAll();
    }

    @Override
    public Inventario findById(Long id){
        return this.inventarioRepository.findById(id).orElseThrow(
                () -> new InventarioException("El inventario con id"+id+"no se encuentra")
        );
    }

    @Override
    public Inventario save(Inventario inventario){
        Producto producto = this.productoClientRest.findById(inventario.getProducto());
        Sucursal sucursal = this.sucursalClientRest.findById();
        Inventario inventarioEntity = new Inventario();
        inventarioEntity.setIdInventario(inventario.getIdInventario());
        inventarioEntity.setStock(inventario.getStock());
        return this.inventarioRepository.save(inventario);
    }
}
