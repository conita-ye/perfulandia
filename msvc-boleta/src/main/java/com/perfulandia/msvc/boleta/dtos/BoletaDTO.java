package com.perfulandia.msvc.boleta.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Boleta")

public class BoletaDTO {
    @JsonIgnore
    private Long id;

    @Schema(description = "ID de la boleta", example = "34322459")
    private Long idBoleta;
    @Schema(description = "Fecha de emisión", example = "2024-06-20")
    private String fechaEmision;
    @Schema(description = "Nombre del cliente", example = "Cely")
    private String nombreCliente;

    @Schema(
            description = "Este es la boleta con la que se trabaja",
            implementation = BoletaDTO.class
    )
    private BoletaDTO boleta;
}
