package com.exemple.msvc.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table (name = "inventario")
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor


public class inventario {

    @Id
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
