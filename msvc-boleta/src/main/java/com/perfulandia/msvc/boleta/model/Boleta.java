package com.perfulandia.msvc.boleta.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;

@Entity
@Table (name = "boletas")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor


public class Boleta {
    @Id
    @GeneratedValue (Strategy = GenerationType.IDENTITY)
    private Long idBoleta;

    @Column (nullable = false)
    @NotNull (message = "El campo no puede estar vacío")
    private Date fechaEmision;

    @Column (nullable = false)
    @NotBlank (message = "El campo de nombre no puede estar vacío")
    private String nombreCliente;

    @Column (nullable = false)
    @NotBlank (message = "El campo de detalle producto no puede estar vacío")
    private String detalleProducto;

    @Column (nullable = false)
    @NotNull (message = "El campo no puede estar vacío")
    private Integer montotal;

}
