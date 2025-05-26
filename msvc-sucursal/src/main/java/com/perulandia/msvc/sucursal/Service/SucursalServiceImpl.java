package com.perulandia.msvc.sucursal.service;

import com.perulandia.msvc.sucursal.model.Sucursal;
import com.perulandia.msvc.sucursal.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.perulandia.msvc.sucursal.service.SucursalService;

import java.util.List;
import java.util.Optional;

@Service
public abstract class SucursalServiceImpl implements SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<Sucursal> listarSucursal(){
        return sucursalRepository.findAll();
    }

    @Override
    public Optional<Sucursal>obtenerSucursal(Long id){
        return sucursalRepository.findById(id);
    }

    @Override
    public Sucursal guardarSucursal(Sucursal sucursal){
        return (Sucursal) sucursalRepository.save(sucursal);
    }

    @Override
    public void eliminarSucursal(Long id){
        sucursalRepository.deleteById(id);
    }
}
