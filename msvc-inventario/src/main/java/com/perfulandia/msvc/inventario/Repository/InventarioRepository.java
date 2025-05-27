package com.perfulandia.msvc.inventario.Repository;


import com.pefulandia.msvc.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long>{
}

