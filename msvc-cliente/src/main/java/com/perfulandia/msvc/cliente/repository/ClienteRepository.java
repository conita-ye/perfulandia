package com.perfulandia.msvc.cliente.repository;

import com.perfulandia.msvc.cliente.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    Iterable<Long> idCliente(Long idCliente);
}
