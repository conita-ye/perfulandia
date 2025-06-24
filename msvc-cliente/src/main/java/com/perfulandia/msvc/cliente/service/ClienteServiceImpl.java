package com.perfulandia.msvc.cliente.service;

import com.perfulandia.msvc.cliente.clients.BoletaClientRest;
import com.perfulandia.msvc.cliente.exceptions.ClienteException;
import com.perfulandia.msvc.cliente.model.entities.Cliente;
import com.perfulandia.msvc.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarCliente () {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        return this.clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException("El cliente con id"+id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public Cliente save (Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente (Long id) {
        clienteRepository.deleteById(id);
    }
}
