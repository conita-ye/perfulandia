package com.macaesp.msvc.carritocompras.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table (name = "carritoCompra")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class carritoCompra {
    @Id
    private int cantiad;
    private double total;
    private String estado;

    public int getCantiad() {
        return cantiad;
    }

    public void setCantiad(int cantiad) {
        this.cantiad = cantiad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
