package com.perfulandia.msvc.carritocompras.service;

import com.perfulandia.msvc.carritocompras.model.CarritoCompra;
import java.util.List;

public interface CarritoCompraService {
    List<CarritoCompra> findAll();
    CarritoCompra findById(Long id);
    CarritoCompra save(CarritoCompra carritoCompra);

}
