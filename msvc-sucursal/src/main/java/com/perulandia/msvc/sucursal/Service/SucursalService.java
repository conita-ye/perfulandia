package com.perulandia.msvc.sucursal.Service;

import com.perulandia.msvc.sucursal.model.Sucursal;
import java.util.List;
import java.util.Optional;

public interface SucursalService {
    public List<Sucursal> listarSucursal();
    public Optional<Sucursal> idSucursal(Long id);
    public Sucursal guardarSucursal(Sucursal sucursal);
    public Sucursal actualizarSucursal(Long id, Sucursal sucursal);
    public void eliminarSucursal(Long id);
}
