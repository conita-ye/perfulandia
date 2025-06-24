package com.perulandia.msvc.sucursal.controller;

import com.perulandia.msvc.sucursal.assemblers.SucursalDTOModelAssembler;
import com.perulandia.msvc.sucursal.assemblers.SucursalModelAssembler;
import com.perulandia.msvc.sucursal.dtos.ErrorDTO;
import com.perulandia.msvc.sucursal.model.entities.Sucursal;
import com.perulandia.msvc.sucursal.service.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("api/v1/sucursales")
@Validated
public class SucursalControllerV2 {
    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private SucursalModelAssembler sucursalModelAssembler;

    @Autowired
    private SucursalDTOModelAssembler sucursalDTOModelAssembler;

    @GetMapping
    @Operation(
            summary = "Devuelve todos las sucursales",
            description = "Este metodo debe retornar un List de Sucursales, en caso "+
                    "de que no encuentre nada retorna una List vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se retornaron todos las sucursales",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class)
                    )
            )
    })
    public ResponseEntity<List<Sucursal>> findAll (){
        return ResponseEntity.status(HttpStatus.OK).body(sucursalService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Devuelve una sucursal por su id",
            description = "Este metodo debe retornar una sucursal por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornó la sucursal encontrada"),
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion hecha con exito",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Sucursal con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value={
            @Parameter(name="id", description="Este es el id unico de la sucursal", required = true)
    })
    public ResponseEntity<Sucursal> findById (@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(sucursalService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Guarda una sucursal por su id",
            description = "Este metodo debe guardar una sucursal por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se guardo la sucursal",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Error - Sucursal con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    public ResponseEntity<Sucursal> save (@RequestBody @Valid Sucursal sucursal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.save(sucursal));
    }

}


