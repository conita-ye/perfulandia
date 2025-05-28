package com.perfulandia.msvc.inventario.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @Column (nullable = false)
    @NotBlank(message = "El campo de Stock no puede estar vacio")
    private int stock;

}
