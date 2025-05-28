package com.perulandia.msvc.sucursal.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "sucursales")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSucursal;

    @NotBlank(message = "El campo de nombre no puede estar vacío")
    @Column (nullable = false)
    private String nombre;

    @NotBlank(message = "El campo de dirección no puede estar vacío")
    @Column (nullable = false)
    private String direccion;

    @NotBlank(message = "El campo de correo electrónico no puede estar vacío")
    @Column (nullable = false)
    private String ciudad;

    @NotNull(message = "El ID de inventario no puede ser nulo")
    @Column(nullable = false)
    private Long idInventario;

}
