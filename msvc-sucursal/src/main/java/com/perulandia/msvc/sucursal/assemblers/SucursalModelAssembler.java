package com.perulandia.msvc.sucursal.assemblers;


import com.perulandia.msvc.sucursal.controller.SucursalControllerV2;
import com.perulandia.msvc.sucursal.model.entities.Sucursal;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>>{
    @Override
    public EntityModel<Sucursal> toModel(Sucursal entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(SucursalControllerV2.class).findById(entity.getIdSucursal())).withSelfRel(),
                linkTo(methodOn(SucursalControllerV2.class).findAll()).withRel("sucursales"),
               // linkTo(methodOn(SucursalControllerV2.class).update(entity.getIdSucursal(), entity)).withRel("update"),
               // linkTo(methodOn(SucursalControllerV2.class).delete(entity.getIdSucursal())).withRel("delete"),
                Link.of("http://localhost:8006/api/v2/inventarios/sucursal/" + entity.getIdSucursal()).withRel("inventarios")
        );
    }

}
