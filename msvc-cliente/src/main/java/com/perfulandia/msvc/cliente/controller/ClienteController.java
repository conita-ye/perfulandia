package com.perfulandia.msvc.cliente.controller;

import com.perfulandia.msvc.cliente.model.Cliente;
import com.perfulandia.msvc.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/clientes")
@Validated
@Tag(name = "Clientes", description = "Esta sección contiene los CRUD de clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(
            summary = "Listar todas las boletas", description = "Este metodo debe listar los clientes")

    @ApiResponse(
            responseCode = "200", description = "Se listaron los clientes")
    public List<Cliente> listarClientes() {return clienteService.listarCliente();}


    @GetMapping ("/{id}")
    public ResponseEntity<Cliente> findById (@PathVariable Long id) {
        Optional<Cliente> cliente = Optional.ofNullable(clienteService.findById(id));
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> save (@RequestBody @Valid Cliente cliente) {
        Cliente nuevoCliente = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente (@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        Optional<Cliente> clienteExistente = Optional.ofNullable(clienteService.findById(id));

        if (clienteExistente.isPresent()) {
            Cliente actualizado = clienteExistente.get();

            actualizado.setIdCliente(cliente.getIdCliente());
            actualizado.setApellCli(cliente.getApellCli());
            actualizado.setTelefono(cliente.getTelefono());
            actualizado.setDireccion(cliente.getDireccion());
            actualizado.setCorreoElectronico(cliente.getCorreoElectronico());

            return ResponseEntity.ok(clienteService.save(actualizado));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente (@PathVariable Long id){
        Optional<Cliente> cliente = Optional.ofNullable(clienteService.findById(id));

        if (cliente.isPresent()){
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
