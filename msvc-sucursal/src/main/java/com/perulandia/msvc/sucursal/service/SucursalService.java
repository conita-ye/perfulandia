package com.perulandia.msvc.sucursal.service;

import com.perulandia.msvc.sucursal.model.Sucursal;
import java.util.List;

public interface SucursalService {
    List<Sucursal> findAll ();
    Sucursal findById (Long id);
    Sucursal save (Sucursal sucursal);
}
