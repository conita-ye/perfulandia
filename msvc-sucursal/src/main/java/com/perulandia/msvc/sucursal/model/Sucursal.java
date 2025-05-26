package com.perulandia.msvc.sucursal.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotBlank(message = "El campo no puede estar vacio")
    @Column(nullable = false)
    private Long Id;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String Nombre;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String Direccion;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String Ciudad;

}
