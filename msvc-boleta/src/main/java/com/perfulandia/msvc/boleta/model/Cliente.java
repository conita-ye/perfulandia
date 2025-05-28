package com.perfulandia.msvc.boleta.model;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Long idCliente;
    private String nombreCli;
    private String apellCli;
    private String direccion;
    private String correoElectronico;
    private Integer telefono;
}