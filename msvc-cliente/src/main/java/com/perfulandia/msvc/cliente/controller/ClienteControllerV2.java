package com.perfulandia.msvc.cliente.controller;

import com.perfulandia.msvc.cliente.assemblers.ClienteModelAssembler;
import com.perfulandia.msvc.cliente.dtos.ErrorDTO;
import com.perfulandia.msvc.cliente.model.Cliente;
import com.perfulandia.msvc.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/boletas")
@Validated
@Tag(name = "Clientes", description = "Esta sección contiene los CRUD de cliente")

public class ClienteControllerV2 {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;


    @GetMapping
    @Operation(
            summary = "Listar todos los clientes", description = "Este metodo debe listar los clientes")
    @ApiResponse(
            responseCode = "200",
            description = "Se listaron los clientes de forma exitosa",
            content = @Content(
                    mediaType = MediaTypes.HAL_JSON_VALUE,
                    schema = @Schema(implementation = Cliente.class)
            )
    )
    public List<Cliente> listarCliente() {
        return clienteService.listarCliente();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener un cliente por su id",
            description = "Este metodo debe obtener un cliente por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación Exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Cliente.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente no encontrado con el id dado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico del producto", required = true)
    })
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Optional<Cliente> cliente = Optional.ofNullable(clienteService.findById(id));
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation( summary = "Agregar un cliente",
            description = "Este método debe agregar un cliente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se agregó el cliente",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Cliente.class)
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Error - Cliente con ID ya existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Este es el cliente a agregar",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class)
            )
    )
    public ResponseEntity<Cliente> agregarCliente (@Valid @RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un cliente  por su id",
            description = "Este metodo debe obtener un cliente por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el cliente encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Cliente  con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id único de clientr ", required = true)
    })
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = Optional.ofNullable(clienteService.findById(id));

        if (clienteExistente.isPresent()) {
            Cliente actualizado = clienteExistente.get();

            actualizado.setIdCliente(cliente.getIdCliente());
            actualizado.setDireccion(actualizado.getDireccion());
            actualizado.setTelefono(actualizado.getTelefono());
            actualizado.setNombreCli(actualizado.getNombreCli());
            actualizado.setApellCli(actualizado.getApellCli());
            actualizado.setCorreoElectronico(actualizado.getCorreoElectronico());


            return ResponseEntity.ok(clienteService.save(actualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un cliente  por su id",
            description = "Este metodo debe eliminar un cliente por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el cliente encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Cliente con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico del cliente", required = true)
    })
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
            Optional<Cliente> cliente = Optional.ofNullable(clienteService.findById(id));

        if (cliente.isPresent()) {
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
