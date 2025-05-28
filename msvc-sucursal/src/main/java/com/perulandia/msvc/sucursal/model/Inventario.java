package com.perulandia.msvc.sucursal.model;


import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    private Long idInventario;
    private int stock;
}
