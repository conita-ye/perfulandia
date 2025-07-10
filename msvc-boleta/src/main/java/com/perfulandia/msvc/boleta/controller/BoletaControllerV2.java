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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v2/boletas")
@Validated
@Tag(name = "Boletas", description = "Esta sección contiene los CRUD de boleta")
public class BoletaControllerV2 {

    @Autowired
    private BoletaService boletaService;

    @Autowired
    private BoletaModelAssembler boletaModelAssembler;

    @GetMapping
    @Operation(summary = "Listar todos las boletas", description = "Este método debe listar las boletas")
    @ApiResponse(
            responseCode = "200",
            description = "Se listaron las boletas de forma exitosa",
            content = @Content(
                    mediaType = MediaTypes.HAL_JSON_VALUE,
                    schema = @Schema(implementation = Boleta.class)
            )
    )
    public ResponseEntity<CollectionModel<EntityModel<Boleta>>> listarBoleta() {
        List<Boleta> boletas = boletaService.listarBoleta();

        List<EntityModel<Boleta>> boletasModel = boletas.stream()
                .map(boletaModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Boleta>> collectionModel = CollectionModel.of(boletasModel,
                linkTo(methodOn(BoletaControllerV2.class).listarBoleta()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una boleta por su id", description = "Este método debe obtener una boleta por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación Exitosa",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Boleta.class))),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters(@Parameter(name = "id", description = "ID único de la boleta", required = true))
    public ResponseEntity<EntityModel<Boleta>> findById(@PathVariable Long id) {
        Optional<Boleta> boleta = Optional.ofNullable(boletaService.findById(id));

        return boleta.map(value -> ResponseEntity.ok(boletaModelAssembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Agregar una boleta", description = "Este método debe agregar una boleta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se agregó la boleta",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Boleta.class))),
            @ApiResponse(responseCode = "400", description = "Error - Boleta con ID ya existe",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<EntityModel<Boleta>> agregarBoleta(@Valid @RequestBody Boleta boleta) {
        Boleta nuevaBoleta = boletaService.save(boleta);
        EntityModel<Boleta> boletaModel = boletaModelAssembler.toModel(nuevaBoleta);

        return ResponseEntity
                .created(boletaModel.getRequiredLink("self").toUri())
                .body(boletaModel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una boleta por su id", description = "Este método debe actualizar una boleta por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se actualizó la boleta",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Boleta.class))),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters(@Parameter(name = "id", description = "ID único de la boleta", required = true))
    public ResponseEntity<EntityModel<Boleta>> actualizarBoleta(@PathVariable Long id, @Valid @RequestBody Boleta boleta) {
        Optional<Boleta> boletaExistente = Optional.ofNullable(boletaService.findById(id));

        if (boletaExistente.isPresent()) {
            Boleta actualizada = boletaExistente.get();

            actualizada.setIdCliente(boleta.getIdCliente());
            actualizada.setFechaEmision(boleta.getFechaEmision());
            actualizada.setNombreCliente(boleta.getNombreCliente());

            Boleta guardada = boletaService.save(actualizada);

            return ResponseEntity.ok(boletaModelAssembler.toModel(guardada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una boleta por su id", description = "Este método debe eliminar una boleta por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Se eliminó la boleta"),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters(@Parameter(name = "id", description = "ID único de la boleta", required = true))
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

