package com.perfulandia.msvc.inventario.repository;

import com.perfulandia.msvc.inventario.model.entities.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {
}
