package com.perfulandia.msvc.inventario.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "El ID del producto no puede ser nulo")
    @Column(nullable = false)
    private Long idProducto;

    @NotNull(message = "El ID de la sucursal no puede ser nulo")
    @Column(nullable = false)
    private Long idSucursal;

}
