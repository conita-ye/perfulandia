package com.perulandia.msvc.sucursal.service;

import com.perulandia.msvc.sucursal.clients.InventarioClientRest;
import com.perulandia.msvc.sucursal.exception.SucursalException;
import com.perulandia.msvc.sucursal.model.entities.Sucursal;
import com.perulandia.msvc.sucursal.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private InventarioClientRest inventarioClientRest;

    @Override
    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal findById(Long id) {
        return sucursalRepository.findById(id).orElseThrow(
                () -> new SucursalException("La sucursal con id " + id + " no se encuentra en la base de datos"));
    }

    @Override
    public Sucursal save(Sucursal sucursal) {
//        try{
//            Inventario inventario = this.inventarioClientRest.findById(sucursal.getIdInventario());
//        }catch (FeignException exception){
//            throw new SucursalException("El inventario con id "+sucursal.getIdInventario() + " no se encuentra en la base de datos"
//                    + " por ende no se puede generar el nexo de relación");
//        }
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal actualizarSucursal(Long id, Sucursal sucursal) {
        Sucursal sucursalBuscada = this.findById(id);
        sucursalBuscada.setNombre(sucursal.getNombre());
        sucursalBuscada.setDireccion(sucursal.getDireccion());
        sucursalBuscada.setCiudad(sucursal.getCiudad());
        return this.sucursalRepository.save(sucursalBuscada);
    }

    @Override
    public Sucursal actualizarParcialSucursal(Long id, Map<String, Object> updates) {
        return null;
    }

    @Override
    public void eliminarSucursal(Long id) {
        sucursalRepository.deleteById(id);
    }
}
