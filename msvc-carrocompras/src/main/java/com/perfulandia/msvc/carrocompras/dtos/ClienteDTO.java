package com.perfulandia.msvc.carrocompras.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Schema(description = "DTO Cliente")
public class ClienteDTO {

    @Schema(description = "ID del cliente", example = "12345")
    private Long id;

    @Schema(description = "Nombre del cliente", example = "constanza")
    private String nombre;

    @Schema(description = "Correo del cliente", example = "Conita@example.com")
    private String correo;
}
