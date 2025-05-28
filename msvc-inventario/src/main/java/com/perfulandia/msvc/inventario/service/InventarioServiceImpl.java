package com.perfulandia.msvc.inventario.service;


import com.perfulandia.msvc.inventario.clients.ProductoClientRest;
import com.perfulandia.msvc.inventario.clients.SucursalClientRest;
import com.perfulandia.msvc.inventario.exception.InventarioException;
import com.perfulandia.msvc.inventario.model.Producto;
import com.perfulandia.msvc.inventario.model.Sucursal;
import com.perfulandia.msvc.inventario.repository.InventarioRepository;
import com.perfulandia.msvc.inventario.model.entities.Inventario;
import feign.FeignException;
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
        try{
            Producto producto = this.productoClientRest.findById(inventario.getIdProducto());
        } catch (FeignException exception) {
            throw new InventarioException("El producto con id"+inventario.getIdProducto()+"no se encuentra en la base de datos"
                    + "por ende no se puede generar el nexo de relacion");
        }

        try{
            Sucursal sucursal = this.sucursalClientRest.findById(inventario.getIdSucursal());
        }catch(FeignException exception){
            throw new InventarioException("la sucursal con id"+inventario.getIdSucursal()+"no se encuentra en la base de datos"
            + "por ende no se puede generar el nexo de relacion");
        }

        Inventario inventarioEntity = new Inventario();
        inventarioEntity.setIdInventario(inventario.getIdInventario());
        inventarioEntity.setStock(inventario.getStock());
        return this.inventarioRepository.save(inventario);
    }
}
