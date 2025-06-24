package com.perfulandia.msvc.boleta.assamblers;

import com.perfulandia.msvc.boleta.model.entities.Boleta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class BoletaModelAssembler implements RepresentationModelAssembler <Boleta, EntityModel<Boleta>> {

    @Override
    public EntityModel<Boleta> toModel(Boleta boleta) {
        return EntityModel.of(
                boleta,
                linkTo );
    }

}
