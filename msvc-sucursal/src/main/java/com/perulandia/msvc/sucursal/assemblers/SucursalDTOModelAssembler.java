package com.perulandia.msvc.sucursal.assemblers;

import com.perulandia.msvc.sucursal.controller.SucursalControllerV2;
import com.perulandia.msvc.sucursal.dtos.SucursalDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SucursalDTOModelAssembler implements RepresentationModelAssembler <SucursalDTO, EntityModel<SucursalDTO>>{
    @Override
    public EntityModel<SucursalDTO> toModel(SucursalDTO entity){
        return EntityModel.of(
                entity,
                linkTo(methodOn(SucursalControllerV2.class).findById(entity.getIdSucursal())).withSelfRel(),
                linkTo(methodOn(SucursalControllerV2.class).findAll()).withRel("sucursales"),
               // linkTo(methodOn(SucursalControllerV2.class).actualizarSucursal(entity.getIdSucursal(), null)).withRel("update"),
              //  linkTo(methodOn(SucursalControllerV2.class).eliminarSucursal(entity.getIdSucursal())).withRel("delete"),
                Link.of("http://localhost:8006/api/v2/inventarios/sucursal/" + entity.getIdSucursal()).withRel("inventarios")
        );
    }
}
