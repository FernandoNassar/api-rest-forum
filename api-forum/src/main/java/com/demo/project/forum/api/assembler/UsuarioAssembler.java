package com.demo.project.forum.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.demo.project.forum.api.entities.dto.usuario.UsuarioResponse;
import com.demo.project.forum.api.resources.UsuarioResource;

@Component
public class UsuarioAssembler implements RepresentationModelAssembler<UsuarioResponse, EntityModel<UsuarioResponse>> {

	@Override
	public EntityModel<UsuarioResponse> toModel(UsuarioResponse entity) {
		
		return EntityModel.of(entity,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioResource.class).getById(entity.getId())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioResource.class).getAll(null)).withRel("usuarios"));
	}

}
