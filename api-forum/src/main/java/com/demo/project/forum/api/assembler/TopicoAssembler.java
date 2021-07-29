package com.demo.project.forum.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.demo.project.forum.api.entities.dto.topico.TopicoResponse;
import com.demo.project.forum.api.resources.TopicoResource;

@Component 
public class TopicoAssembler implements RepresentationModelAssembler<TopicoResponse, EntityModel<TopicoResponse>> {

	@Override
	public EntityModel<TopicoResponse> toModel(TopicoResponse entity) {
		
		return EntityModel.of(entity,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TopicoResource.class).getById(entity.getId())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TopicoResource.class).usuarioByTopico(entity.getId(), null)).withRel("usuario"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TopicoResource.class).respostasByTopico(entity.getId(), null)).withRel("respostas"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TopicoResource.class).getAll(null)).withRel("topicos"));
	}

}
