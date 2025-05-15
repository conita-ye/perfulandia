package com.exemple.msvc.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table (name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class inventario {

    @Id
    private int estock;

    public int getEstock() {
        return estock;
    }

    public void setEstock(int estock) {
        this.estock = estock;
    }
}
