package com.perfulandia.msvc.inventario.service;


import com.perfulandia.msvc.inventario.exception.InventarioException;
import com.perfulandia.msvc.inventario.model.Inventario;
import com.perfulandia.msvc.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService{
    @Autowired
    private InventarioRepository inventarioRepository;

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
        Inventario inventarioEntity = new Inventario();
        inventarioEntity.setIdInventario(inventario.getIdInventario());
        inventarioEntity.setStock(inventario.getStock());
        return this.inventarioRepository.save(inventario);
    }
}
