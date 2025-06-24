package com.perfulandia.msvc.producto.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.perfulandia.msvc.producto.model.entities.Producto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "DTO Producto")
public class ProductoDTO {

    @JsonIgnore
    private Long id;

    @Schema(description = "Nombre del producto", example = "Papas")
    private String nombreProducto;
    @Schema(description = "Fecha de elaboracion", example = "2021-01-01")
    private String fechaElaboracion;
    @Schema(description = "Fecha de vencimiento", example = "2021-01-31")
    private String fechaVencimiento;
    @Schema(description = "Categoria", example = "Frutas")
    private String categoria;
    @Schema(description = "Stock", example = "100")
    private int stock;
    @Schema(description = "Precio", example = "1.00")
    private double precio;

    @Schema(
            description = "Este es el producto con el que se trabaja",
            implementation = ProductoDTO.class
    )
    private ProductoDTO producto;
}
