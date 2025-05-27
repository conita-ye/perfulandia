package com.perfulandia.msvc.carrocompras.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull;
import lombok.*;

 @Entity
 @Table(name = "carrocompras")
 @Getter @Setter @ToString
 @NoArgsConstructor
 @AllArgsConstructor

public class CarroCompras {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
     private long idCarroCompras;

    @NotBlank(message = "El campo no puede estar vacio")
     @Column(nullable = false)
     private String idProducto;

     @Min(value = 1, message = "La cantidad debe ser al menos 1")
     @Column(nullable = false)
     private int cantidadProducto;

     @NotNull(message = "El precio unitario no puede ser nulo")
     @Column(nullable = false)
     private Double precioUnitario;

}

