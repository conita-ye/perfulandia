package com.perulandia.msvc.sucursal.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "DTO Sucursal")
public class SucursalDTO {
    @JsonIgnore
    private Long idSucursal;

    @Schema(description = "Nombre de la Sucursal", example = "SucursalPerma")
    private String nombre;
    @Schema(description = "Direccion de la Sucursal", example = "Calle 123")
    private String direccion;
    @Schema(description = "Ciudad de la sucursal", example = "Valparaiso")
    private String ciudad;

    @Schema(
            description = "Este es la sucursal ccon la que se trabaja",
            implementation = SucursalDTO.class
    )
    private SucursalDTO sucursal;
}
