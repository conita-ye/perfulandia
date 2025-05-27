package com.perfulandia.msvc.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stock;

}
