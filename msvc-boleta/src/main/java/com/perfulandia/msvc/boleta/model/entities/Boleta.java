package com.perfulandia.msvc.boleta.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "El ID del carro de compras no puede ser nulo")
    @Column(nullable = false)
    private Long idCarroCompras;

    @NotNull(message = "El ID del cliente no puede ser nulo")
    @Column(nullable = false)
    private Long idCliente;

    @NotNull(message = "El ID del producto no puede ser nulo")
    @Column(nullable = false)
    private Long idProducto;
}
