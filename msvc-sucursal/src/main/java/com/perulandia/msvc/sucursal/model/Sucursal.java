package com.perulandia.msvc.sucursal.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "sucursales")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor

public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String Nombre;

    private String Direccion;

    private String Ciudad;

}
