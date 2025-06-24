package com.perfulandia.msvc.producto.assemblers;


import com.perfulandia.msvc.producto.controller.ProductoControllerV2;
import com.perfulandia.msvc.producto.model.entities.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {
    @Override
    public EntityModel<Producto> toModel(Producto entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProductoControllerV2.class).findById(entity.getIdProducto())).withSelRel(),
                linkTo(methodOn(ProductoControllerV2.class).findAll()).withRel("productos"),
                Link.of("http://localhost:8005/api/v2/productos"+entity.getIdProducto()).withRel("producto"),
                Link.of("")
        );
    }
}
