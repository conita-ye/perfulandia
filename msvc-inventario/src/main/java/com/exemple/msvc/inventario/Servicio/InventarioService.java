package com.exemple.msvc.inventario.Servicio;


import com.exemple.msvc.inventario.Servicio.
import com.exemple.msvc.inventario.Repository.InventarioRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    @Autowired
    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    // Obtener todos los inventarios
    public List<Inventario> obtenerTodos() {
        return inventarioRepository.findAll();
    }

    // Obtener un inventario por su ID
    public Optional<Inventario> obtenerPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    // Crear o actualizar un inventario
    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    // Eliminar un inventario por su ID
    public void eliminar(Long id) {
        inventarioRepository.deleteById(id);
    }
}

