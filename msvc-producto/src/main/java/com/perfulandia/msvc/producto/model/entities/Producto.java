package com.perfulandia.msvc.producto.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="productos")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String nombreProducto;

    @NotBlank(message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private Date fechaElaboracion;

    @NotBlank(message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String fechaVencimiento;

    @NotBlank(message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String categoria;

    @NotNull (message = "El Stock no puede ser nulo")
    @Column (nullable = false)
    private int stock;

    @NotNull (message = "El precio no puede ser nulo")
    @Column (nullable = false)
    private double precio;

    @NotNull(message = "El ID de inventario no puede ser nulo")
    @Column(nullable = false)
    private Long idInventario;

}
