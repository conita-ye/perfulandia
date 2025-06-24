package com.perfulandia.msvc.boleta.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idCliente;

    @NotBlank(message = "El campo de nombre no puede estar vacío")
    @Column(nullable = false)
    private String nombreCli;

    @NotBlank(message = "El campo de apellido no puede estar vacío")
    @Column (nullable = false)
    private String apellCli;

    @NotBlank(message = "El campo de dirección no puede estar vacío")
    @Column (nullable = false)
    private String direccion;

    @NotBlank(message = "El campo de correo electronico no puede estar vacío")
    @Column (nullable = false)
    private String correoElectronico;

    @NotNull(message = "El numero no puede ser nulo")
    @Column(nullable = false)
    private Integer telefono;
}