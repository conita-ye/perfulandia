package com.perfulandia.msvc.cliente.service;

import com.perfulandia.msvc.cliente.model.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
}
