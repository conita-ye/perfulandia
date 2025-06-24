package com.perfulandia.msvc.producto.assemblers;

import com.perfulandia.msvc.producto.controller.ProductoControllerV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoDTOModelAssembler implements RepresentationModelAssembler<ProductoDTO, EntityModel<ProductoDTO>> {
    @Override
    public EntityModel<ProductoDTO> toModel(ProductoDTO entity){
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProductoControllerV2.class).findById(entity.getId()))).withRel("productos");
    }
}
