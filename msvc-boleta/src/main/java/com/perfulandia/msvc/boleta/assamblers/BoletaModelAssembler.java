package com.perfulandia.msvc.boleta.assamblers;

import com.perfulandia.msvc.boleta.controller.BoletaControllerV2;
import com.perfulandia.msvc.boleta.model.entities.Boleta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BoletaModelAssembler implements RepresentationModelAssembler<Boleta, EntityModel<Boleta>> {

    @Override
    public EntityModel<Boleta> toModel(Boleta boleta) {
        return EntityModel.of(boleta,
                linkTo(methodOn(BoletaControllerV2.class).findById(boleta.getIdBoleta())).withSelfRel(),
                linkTo(methodOn(BoletaControllerV2.class).listarBoleta()).withRel("boletas"));
    }
}
