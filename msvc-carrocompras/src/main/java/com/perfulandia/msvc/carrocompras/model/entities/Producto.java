package com.perfulandia.msvc.carrocompras.model.entities;


import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

}
