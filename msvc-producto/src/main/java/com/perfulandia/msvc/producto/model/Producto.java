package com.perfulandia.msvc.producto.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table (name = "productos")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombreProducto;

    private Date fechaElaboracion;

    private Date fechaVencimiento;

    private String catergoria;

    private int stock;

    private double precio;


}
