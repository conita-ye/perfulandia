package com.perfulandia.msvc.boleta.controller;

import com.perfulandia.msvc.boleta.assamblers.BoletaModelAssembler;
import com.perfulandia.msvc.boleta.dtos.ErrorDTO;
import com.perfulandia.msvc.boleta.model.entities.Boleta;
import com.perfulandia.msvc.boleta.service.BoletaService;
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
@Tag(name = "Boletas", description = "Esta sección contiene los CRUD de boleta")

public class BoletaControllerV2 {

    @Autowired
    private BoletaService boletaService;

    @Autowired
    private BoletaModelAssembler boletaModelAssembler;


    @GetMapping
    @Operation(
            summary = "Listar todos las boletas", description = "Este metodo debe listar las boletas")
    @ApiResponse(
            responseCode = "200",
            description = "Se listaron las boletas de forma exitosa",
            content = @Content(
                    mediaType = MediaTypes.HAL_JSON_VALUE,
                    schema = @Schema(implementation = Boleta.class)
            )
    )
    public List<Boleta> listarBoleta() {
        return boletaService.listarBoleta();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener una boleta por su id",
            description = "Este metodo debe obtener una boleta por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación Exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Boleta.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Boleta no encontrada con el id dado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico del producto", required = true)
    })
    public ResponseEntity<Boleta> findById(@PathVariable Long id) {
        Optional<Boleta> boleta = Optional.ofNullable(boletaService.findById(id));
        return boleta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation( summary = "Agregar una boleta",
            description = "Este método debe agregar una boleta"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se agrego la boleta",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Boleta.class)
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Error - Boleta con ID ya existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Esta es la boleta a agregar",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Boleta.class)
            )
    )
    public ResponseEntity<Boleta> agregarBoleta (@Valid @RequestBody Boleta boleta) {
        Boleta nuevaBoleta = boletaService.save(boleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaBoleta);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar una boleta  por su id",
            description = "Este metodo debe obtener una boleta por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna la boleta encontrada"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Boleta con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id único de boleta ", required = true)
    })
    public ResponseEntity<Boleta> actualizarBoleta(@PathVariable Long id, @Valid @RequestBody Boleta boleta) {
        Optional<Boleta> boletaExistente = Optional.ofNullable(boletaService.findById(id));

        if (boletaExistente.isPresent()) {
            Boleta actualizada = boletaExistente.get();

            actualizada.setIdBoleta(boleta.getIdBoleta());
            actualizada.setIdCliente(boleta.getIdCliente());
            actualizada.setIdProducto(boleta.getIdProducto());
            actualizada.setFechaEmision(boleta.getFechaEmision());
            actualizada.setNombreCliente(boleta.getNombreCliente());

            return ResponseEntity.ok(boletaService.save(actualizada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar una boleta  por su id",
            description = "Este metodo debe eliminar una boleta por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la boleta encontrada"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Boleta con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico de la boleta", required = true)
    })
    public ResponseEntity<Void> eliminarBoleta(@PathVariable Long id) {
        Optional<Boleta> boleta = Optional.ofNullable(boletaService.findById(id));

        if (boleta.isPresent()) {
            boletaService.eliminarBoleta(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
