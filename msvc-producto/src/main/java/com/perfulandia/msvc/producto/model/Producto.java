package com.perfulandia.msvc.producto.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String nombreProducto;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private Date fechaElaboracion;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private Date fechaVencimiento;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String catergoria;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private int stock;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private double precio;


}
