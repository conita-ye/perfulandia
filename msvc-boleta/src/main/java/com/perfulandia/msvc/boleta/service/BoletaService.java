package com.perfulandia.msvc.boleta.service;

import com.perfulandia.msvc.boleta.model.entities.Boleta;

import java.util.List;

public interface BoletaService {
    List<Boleta> finAll();
    Boleta findById (Long id);
    Boleta save (Boleta boleta);
}
