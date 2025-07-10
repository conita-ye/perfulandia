package com.perfulandia.msvc.carrocompras.controller;

import com.perfulandia.msvc.carrocompras.assembler.CarroComprasModelAssembler;
import com.perfulandia.msvc.carrocompras.dtos.ErrorDTO;
import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v2/carro-compras")
@Validated
@Tag(name = "CarroCompras", description = "Esta sección contiene los CRUD del Carro de Compras")
public class CarroComprasControllerV2 {

    @Autowired
    private CarrocomprasService carroComprasService;

    @Autowired
    private CarroComprasModelAssembler carroComprasModelAssembler;

    @GetMapping
    @Operation(summary = "Listar todos los carros de compras", description = "Este método lista todos los carros de compras")
    @ApiResponse(responseCode = "200", description = "Listado exitoso",
            content = @Content(mediaType = MediaType.HAL_JSON_VALUE, schema = @Schema(implementation = CarroCompras.class)))
    public ResponseEntity<CollectionModel<EntityModel<CarroCompras>>> listarCarros() {
        List<CarroCompras> carros = carroComprasService.findAll();
        List<EntityModel<CarroCompras>> carrosModel = carros.stream()
                .map(carroComprasModelAssembler::toModel)
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(carrosModel, linkTo(methodOn(CarroComprasControllerV2.class).listarCarros()).withSelfRel())
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener carro por ID", description = "Este método obtiene un carro de compras por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro encontrado",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = CarroCompras.class))),
            @ApiResponse(responseCode = "404", description = "Carro no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters(@Parameter(name = "id", description = "ID único del carro de compras", required = true))
    public ResponseEntity<EntityModel<CarroCompras>> obtenerCarroPorId(@PathVariable Long id) {
        Optional<CarroCompras> carro = carroComprasService.findById(id);

        return carro.map(c -> ResponseEntity.ok(carroComprasModelAssembler.toModel(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Agregar nuevo carro de compras", description = "Este método crea un nuevo carro de compras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carro creado",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = CarroCompras.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<EntityModel<CarroCompras>> crearCarro(@Valid @RequestBody CarroCompras carro) {
        CarroCompras nuevo = carroComprasService.save(carro);
        EntityModel<CarroCompras> carroModel = carroComprasModelAssembler.toModel(nuevo);

        return ResponseEntity.created(carroModel.getRequiredLink("self").toUri())
                .body(carroModel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar carro", description = "Este método actualiza los datos de un carro de compras por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro actualizado",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = CarroCompras.class))),
            @ApiResponse(responseCode = "404", description = "Carro no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<EntityModel<CarroCompras>> actualizarCarro(@PathVariable Long id, @Valid @RequestBody CarroCompras carro) {
        Optional<CarroCompras> existente = carroComprasService.findById(id);

        if (existente.isPresent()) {
            CarroCompras actual = existente.get();

            actual.setClienteId(carro.getClienteId());
            actual.setProductos(carro.getProductos());
            actual.setTotal(carro.getTotal());

            CarroCompras actualizado = carroComprasService.save(actual);

            return ResponseEntity.ok(carroComprasModelAssembler.toModel(actualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar carro", description = "Este método elimina un carro de compras por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carro eliminado"),
            @ApiResponse(responseCode = "404", description = "Carro no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<Void> eliminarCarro(@PathVariable Long id) {
        Optional<CarroCompras> carro = carroComprasService.findById(id);

        if (carro.isPresent()) {
            carroComprasService.eliminarCarro(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
