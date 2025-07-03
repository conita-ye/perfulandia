package com.perfulandia.msvc.cliente.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Cliente")

public class ClienteDTO {
    @JsonIgnore
    private Long id;

    @Schema (description = "Nombre del cliente", example = "Cely")
    private String nombreCli;
    @Schema (description = "Apellido del cliente", example = "Escobar")
    private String apellCli;
    @Schema (description = "Dirección", example = "Viña del Mar")
    private String direccion;
    @Schema (description = "Correo electrónico", example = "ara.escobar@duocuc.cl")
    private String correoElectronico;
    @Schema (description = "Número telefonico", example = "56903434594")
    private Integer telefono;

    @Schema(
            description = "Este es el cliente con el que se trabaja",
            implementation = ClienteDTO.class
    )
    private ClienteDTO cliente;

}
