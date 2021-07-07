package com.demo.project.forum.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.demo.project.forum.api.model.dto.usuario.UsuarioResponse;

public class UsuarioAssembler implements RepresentationModelAssembler<UsuarioResponse, EntityModel<UsuarioResponse>> {

	@Override
	public EntityModel<UsuarioResponse> toModel(UsuarioResponse entity) {
		return null;
	}

}
