package com.perfulandia.msvc.boleta.repository;

import com.perfulandia.msvc.boleta.model.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {
    Iterable<Long> idBoleta (Long idBoleta);
}
