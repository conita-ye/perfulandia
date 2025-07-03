package com.perfulandia.msvc.producto.controller;

import com.perfulandia.msvc.producto.assemblers.ProductoModelAssembler;
import com.perfulandia.msvc.producto.dtos.ErrorDTO;
import com.perfulandia.msvc.producto.model.entities.Producto;
import com.perfulandia.msvc.producto.service.ProductoService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("api/v2/productos")
@Validated
@Tag(name = "Productos", description = "Esta seccion contiene los CRUD de producto")

public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler productoModelAssembler;


    @GetMapping
    @Operation(
            summary = "Listar todos los productos", description = "Este metodo debe listar los productos")
    @ApiResponse(
            responseCode = "200",
            description = "Se listaron los productos de forma exitosa",
            content = @Content(
                    mediaType = MediaTypes.HAL_JSON_VALUE,
                    schema = @Schema(implementation = Producto.class)
            )
    )
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> listarProducto() {
        List<EntityModel<Producto>> entityModels = productoService.listarProducto()
                .stream()
                .map(productoModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Producto>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(ProductoControllerV2.class).listarProducto()).withSelfRel()
        );
        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener un producto por su id",
            description = "Este metodo debe obtener un producto por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion Exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Producto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado con el id dado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico del producto", required = true)
    })
    public ResponseEntity<EntityModel<Producto>> findById(@PathVariable Long id) {
        EntityModel<Producto> entityModel = productoModelAssembler.toModel(productoService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(entityModel);
    }

    @PostMapping
    @Operation( summary = "Agregar un producto",
            description = "Este metodo debe agregar un producto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                        description = "Se agrego el producto",
                        content = @Content(
                                mediaType = MediaTypes.HAL_JSON_VALUE,
                                schema = @Schema(implementation = Producto.class)
                        )
            ),
            @ApiResponse(responseCode = "400",
                        description = "Error - Producto con ID ya existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Este es el producto a agregar",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class)
            )
    )
    public ResponseEntity<EntityModel<Producto>> agregarProducto(@Valid @RequestBody Producto producto) {
        Producto nuevoProducto = productoService.guardarProducto(producto);
        EntityModel<Producto> entityModel = productoModelAssembler.toModel(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un producto  por su id",
            description = "Este metodo debe obtener un producto por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el producto encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Producto con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico del producto", required = true)
    })
    public ResponseEntity<EntityModel<Producto>> actualizarProducto(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        EntityModel<Producto> entityModel = productoModelAssembler.toModel(productoService.actualizarProducto(id, producto));
        return ResponseEntity.status(HttpStatus.OK).body(entityModel);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un producto  por su id",
            description = "Este metodo debe eliminar un producto por su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el producto encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Producto con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico del producto", required = true)
    })
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        Optional<Producto> producto = Optional.ofNullable(productoService.findById(id));

        if (producto.isPresent()) {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
