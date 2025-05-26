package com.perulandia.msvc.sucursal.service;

import com.perulandia.msvc.sucursal.model.Sucursal;
import java.util.List;
import java.util.Optional;

public interface SucursalService {
    public List<Sucursal> listarSucursal();
    public Optional<Sucursal> idSucursal(Long id);
    Optional<Sucursal>obtenerSucursal(Long id);
    public Sucursal guardarSucursal(Sucursal sucursal);
    public void eliminarSucursal(Long id);
}
