package com.perfulandia.msvc.boleta.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long idBoleta;

    @Column(nullable = false, unique = true)
    private Long numBoleta;

    @NotBlank(message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String fechaEmision;

    @NotBlank (message = "El campo no puede estar vacio")
    @Column (nullable = false)
    private String nombreCliente;
}
