package com.perfulandia.msvc.inventario.assembler;

import com.perfulandia.msvc.inventario.controller.InventarioController;
import com.perfulandia.msvc.inventario.model.entities.Inventario;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public EntityModel<Inventario> toModel(Inventario inventario) {
        return EntityModel.of(
                inventario,
                linkTo(methodOn(InventarioController.class).findById(inventario.getIdInventario())).withSelfRel(),
                linkTo(methodOn(InventarioController.class).findAll()).withRel("inventario")
        );
    }
}

