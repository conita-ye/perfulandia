package com.perfulandia.msvc.cliente.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false)
    @NotBlank(message = "El campo de nombre no puede estar vacío")
    private String nombreCli;

    @Column (nullable = false)
    @NotBlank (message = "El campo de apellido no puede estar vacío")
    private String apellCli;

    @Column (nullable = false)
    @NotBlank (message = "El campo direccion no puede estar vacío")
    private String direccion;

    @Column (nullable = false)
    @NotBlank (message = "El campo correo electrónico no puede estar vacío")
    private String correoElectronico;

    @Column (nullable = false)
    @NotNull(message = "El campo no puede estar vacío")
    private Integer telefono;

    @NotNull(message = "El ID de boleta no puede ser nulo")
    @Column(nullable = false)
    private Long idBoleta;
}
