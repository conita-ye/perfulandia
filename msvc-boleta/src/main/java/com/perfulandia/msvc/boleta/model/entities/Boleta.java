package com.perfulandia.msvc.boleta.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "boletas")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idBoleta;

    @NotBlank(message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String fechaEmision;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String nombreCliente;
}
