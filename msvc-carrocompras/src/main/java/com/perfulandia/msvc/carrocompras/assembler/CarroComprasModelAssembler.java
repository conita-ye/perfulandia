package com.perfulandia.msvc.carrocompras.assembler;


import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;

import org.springframework.hateoas.EntityModel;
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
                linkTo(methodOn(com.perfulandgia.msvc.carrocompras.controller.CarroComprasController.class).findById(carroCompras.getIdBoleta())).withSelfRel(),
                linkTo(methodOn(com.perfulandgia.msvc.carrocompras.controller.CarroComprasController.class).findAll()).withRel("carrocompras")
        );
    }
}