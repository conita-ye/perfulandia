package com.perfulandia.msvc.carritocompras.repository;


import com.perfulandia.msvc.carritocompras.model.CarritoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoCompraRepository extends JpaRepository<CarritoCompra, Long> {
    // Métodos CRUD ya están disponibles gracias a JpaRepository
}
