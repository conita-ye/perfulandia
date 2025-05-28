package com.perfulandia.msvc.inventario.model;



import lombok.*;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private Long id;
    private String nombreProducto;
    private Date fechaElaboracion;
    private Date fechaVencimiento;
    private String categoria;
    private int stock;
    private double precio;
}
