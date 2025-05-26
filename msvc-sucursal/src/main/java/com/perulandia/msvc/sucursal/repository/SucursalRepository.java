package com.perulandia.msvc.sucursal.repository;

import com.perulandia.msvc.sucursal.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long>{
}
