package com.perulandia.msvc.sucursal.Service;

import com.perulandia.msvc.sucursal.model.Sucursal;
import com.perulandia.msvc.sucursal.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class SucursalServiceImpl implements SucursalService{
    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<Sucursal>listarSucursal(){
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
    public Sucursal actualizarSucursal(Long id, Sucursal sucursal){
        Optional<Sucursal> existeSucursal = sucursalRepository.findById(id);
        if(existeSucursal.isPresent()){
            Sucursal s = existeSucursal.get();
            s.setNombre(sucursal.getNombre());
            s.setDireccion(sucursal.getDireccion());
            s.setCiudad(sucursal.getCiudad());
            return (Sucursal) sucursalRepository.save(s);
        }else{
            throw new RuntimeException("La Sucursal buscada por id no se encuentra" + id);
        }
    }

    @Override
    public void eliminarSucursal(Long id){
        sucursalRepository.deleteById(id);
    }
}
