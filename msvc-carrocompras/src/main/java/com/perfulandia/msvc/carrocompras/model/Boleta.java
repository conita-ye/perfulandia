package com.perfulandia.msvc.carrocompras.model;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Boleta {

    private Long idBoleta;
    private String fechaEmision;
    private String nombreCliente;

}
