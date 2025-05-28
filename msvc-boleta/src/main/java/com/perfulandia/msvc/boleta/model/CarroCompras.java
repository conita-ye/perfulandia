package com.perfulandia.msvc.boleta.model;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarroCompras {

    private long idCarroCompras;
    private String idProducto;
    private int cantidadProducto;
    private Double precioUnitario;
    private Long idCliente;
    private Long idBoleta;

}
