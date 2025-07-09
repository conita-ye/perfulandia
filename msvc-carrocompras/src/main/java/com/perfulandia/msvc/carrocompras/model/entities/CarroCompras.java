package com.perfulandia.msvc.carrocompras.model.entities;

import com.perfulandia.msvc.carrocompras.model.Boleta;
import com.perfulandia.msvc.carrocompras.model.Cliente;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "carrocompras")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarroCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCarroCompras;

    @NotNull(message = "El ID del producto no puede ser nulo")
    @Min(value = 1, message = "El ID del producto debe ser un valor positivo")
    @Column(nullable = false)
    private Long idProducto; // Cambié de String a Long

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Column(nullable = false)
    private int cantidadProducto;

    @NotNull(message = "El precio unitario no puede ser nulo")
    @Column(nullable = false)
    private Double precioUnitario;

    @NotNull(message = "El ID del cliente no puede ser nulo")
    @Column(nullable = false)
    private Long idCliente;

    @NotNull(message = "El ID de la boleta no puede ser nulo")
    @Column(nullable = false)
    private Long idBoleta;

    // Relación con Producto (uno a muchos)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto", insertable = false, updatable = false)
    private Producto producto;

    // Relación con Cliente (uno a muchos)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente", insertable = false, updatable = false)
    private Cliente cliente;

    // Relación con Boleta (uno a muchos)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBoleta", referencedColumnName = "idBoleta", insertable = false, updatable = false)
    private Boleta boleta;
}
