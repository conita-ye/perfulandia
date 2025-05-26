package com.perulandia.msvc.sucursal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table (name = "sucursales")
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotBlank(message = "El campo no puede estar vacio")
    @Column(nullable = false)
    private Long idSucursal;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String nombre;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String direccion;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String ciudad;

}
