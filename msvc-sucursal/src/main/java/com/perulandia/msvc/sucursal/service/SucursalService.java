package com.perulandia.msvc.sucursal.service;

import com.perulandia.msvc.sucursal.model.entities.Sucursal;
import java.util.List;
import java.util.Map;

public interface SucursalService {
    List<Sucursal> findAll();
    Sucursal findById(Long id);
    Sucursal save(Sucursal sucursal);
    Sucursal actualizarSucursal(Long id, Sucursal sucursal);
    Sucursal actualizarParcialSucursal(Long id, Map<String, Object> updates);
    void eliminarSucursal(Long id);
}
