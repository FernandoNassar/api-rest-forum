package com.demo.project.forum.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.demo.project.forum.api.entities.dto.resposta.RespostaResponse;
import com.demo.project.forum.api.resources.RespostaResource;

@Component
public class RespostaAssembler implements RepresentationModelAssembler<RespostaResponse, EntityModel<RespostaResponse>> {

	@Override
	public EntityModel<RespostaResponse> toModel(RespostaResponse entity) {

		return EntityModel.of(entity, 
				linkTo(methodOn(RespostaResource.class).getById(entity.getId())).withSelfRel(),
				linkTo(methodOn(RespostaResource.class).topicoByResposta(entity.getId())).withRel("topico"),
				linkTo(methodOn(RespostaResource.class).usuarioByResposta(entity.getId())).withRel("usuario"),
				linkTo(methodOn(RespostaResource.class).getAll(null)).withRel("todos"));
	}
	
	
	
}
