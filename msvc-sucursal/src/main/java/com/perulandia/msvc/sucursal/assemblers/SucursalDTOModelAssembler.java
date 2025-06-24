package com.perulandia.msvc.sucursal.assemblers;

import com.perulandia.msvc.sucursal.controller.SucursalControllerV2;
import com.perulandia.msvc.sucursal.dtos.SucursalDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SucursalDTOModelAssembler implements RepresentationModelAssembler <SucursalDTO, EntityModel<SucursalDTO> {
    @Override
    public EntityModel<SucursalDTO> toModel(SucursalDTO entity){
        return EntityModel.of(
                entity,
                linkTo(methodOn(SucursalControllerV2.class).findById(entity.getIdSucursal())).withRel("sucursales")
        );
    }
}
