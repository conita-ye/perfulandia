package com.perfulandia.msvc.inventario.repository;

import com.perfulandia.msvc.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario,Long> {
}
