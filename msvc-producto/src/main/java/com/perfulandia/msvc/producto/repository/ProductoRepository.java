package com.perfulandia.msvc.producto.repository;

import com.perfulandia.msvc.producto.model.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
