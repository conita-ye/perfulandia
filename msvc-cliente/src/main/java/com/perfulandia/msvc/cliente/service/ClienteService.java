package com.perfulandia.msvc.cliente.service;

import com.perfulandia.msvc.cliente.model.entities.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> listarCliente();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void eliminarCliente(Long id);
}
