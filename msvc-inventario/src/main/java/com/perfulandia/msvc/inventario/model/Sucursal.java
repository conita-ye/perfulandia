package com.perfulandia.msvc.inventario.model;


import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
    private Long idSucursal;
    private String nombre;
    private String direccion;
    private String ciudad;
}
