package com.perfulandia.msvc.cliente.service;

import com.perfulandia.msvc.cliente.exceptions.ClienteException;
import com.perfulandia.msvc.cliente.model.Cliente;
import com.perfulandia.msvc.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.util.List;

public class ClienteServiceImpl {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }
    @Override
    public Cliente findById(Long id){
        return this.clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException("El cliente con id"+id+"no se encuentra en la base de datos")
        );
    }

    @Override
    public Cliente save(Cliente cliente){
        Cliente clienteEntity = new Cliente();
        cliente.setNombreCli(cliente.getNombreCli());
        cliente.setApellCli(cliente.getApellCli());
        cliente.setTelefono(cliente.getTelefono());
        cliente.setDireccion(cliente.getDireccion());
        cliente.setCorreoElectronico(cliente.getCorreoElectronico());
        return this.clienteRepository.save(cliente);
    }
}
