package com.perfulandia.msvc.carrocompras.assembler;


import com.perfulandia.msvc.carrocompras.controller.CarroComprasController;
import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class CarroComprasModelAssembler implements RepresentationModelAssembler<CarroCompras, EntityModel<CarroCompras>> {

    @Override
    public EntityModel<CarroCompras> toModel(CarroCompras carroCompras) {
        return EntityModel.of(
                carroCompras,
                linkTo(methodOn(CarroComprasController.class).findById(carroCompras.getIdBoleta())).withSelfRel(),
                linkTo(methodOn(CarroComprasController.class).findAll()).withRel("carrocompras"),
                Link.of("http://localhost:8005/api/v2/productos/"+carroCompras.getIdProducto()).withRel("producto"),
                Link.of("http://localhost:8003/api/v2/clientes/"+carroCompras.getIdCliente()).withRel("cliente"),
                Link.of("http://localhost:8005/api/v2/boletas/"+carroCompras.getIdBoleta()).withRel("boleta")

        );
    }
}