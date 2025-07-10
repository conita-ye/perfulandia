package com.perfulandia.msvc.carrocompras.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO Carro de Compras")
public class CarroComprasDTO {

    @JsonIgnore
    private Long id;

    @Schema(description = "ID del carro de compras", example = "87612345")
    private Long idCarro;

    @Schema(description = "ID del cliente asociado al carro", example = "11223344")
    private Long idCliente;

    @Schema(description = "Estado del carro de compras", example = "ACTIVO")
    private String estado;

    @Schema(
            description = "Este es el carro de compras con el que se trabaja",
            implementation = CarroComprasDTO.class
    )
    private CarroComprasDTO carroCompras;
}
