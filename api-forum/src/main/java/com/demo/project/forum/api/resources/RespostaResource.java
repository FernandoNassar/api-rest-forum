package com.demo.project.forum.api.resources;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.forum.api.assembler.RespostaAssembler;
import com.demo.project.forum.api.assembler.TopicoAssembler;
import com.demo.project.forum.api.assembler.UsuarioAssembler;
import com.demo.project.forum.api.entities.Resposta;
import com.demo.project.forum.api.entities.Topico;
import com.demo.project.forum.api.entities.Usuario;
import com.demo.project.forum.api.entities.dto.resposta.RespostaRequest;
import com.demo.project.forum.api.entities.dto.resposta.RespostaResponse;
import com.demo.project.forum.api.entities.dto.topico.TopicoResponse;
import com.demo.project.forum.api.entities.dto.usuario.UsuarioResponse;
import com.demo.project.forum.api.services.RespostaService;

@RestController
@RequestMapping(path = "/respostas")
public class RespostaResource {

	@Autowired
	private RespostaService respostaService;
	
	@Autowired
	private RespostaAssembler respostaAssembler;
	
	@Autowired
	private UsuarioAssembler usuarioAssembler;
	
	@Autowired
	private TopicoAssembler topicoAssembler;
	
	@Autowired
	private PagedResourcesAssembler<RespostaResponse> respostaResourcesAssembler;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<EntityModel<RespostaResponse>> getById(@PathVariable("id") Integer id) {
		
		Resposta resposta = respostaService.findById(id);
		
		RespostaResponse response = modelMapper.map(resposta, RespostaResponse.class);
		
		return ResponseEntity.ok(respostaAssembler.toModel(response));
	}
	
	
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(
			@PageableDefault(sort="id", size=10, direction=Direction.ASC) Pageable pageable) {
		
		Page<Resposta> respostas = respostaService.findAll(pageable);
		
		Page<RespostaResponse> respostasResponse = respostas.map(r -> modelMapper.map(r, RespostaResponse.class));
		
		return ResponseEntity.ok(respostaResourcesAssembler.toModel(respostasResponse, respostaAssembler));
	}
	
	
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<EntityModel<RespostaResponse>> create(@RequestBody @Valid RespostaRequest request) {
		
		Resposta resposta = modelMapper.map(request, Resposta.class);
		
		resposta = respostaService.save(resposta);
		
		RespostaResponse response = modelMapper.map(resposta, RespostaResponse.class);
		
		EntityModel<RespostaResponse> entityModel = respostaAssembler.toModel(response);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	
	
	
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, 
			@RequestBody @Valid RespostaRequest request) {
		
		Resposta resposta = respostaService.update(id, request);
		
		RespostaResponse response = modelMapper.map(resposta, RespostaResponse.class);
		
		return ResponseEntity.ok(respostaAssembler.toModel(response)); 
	}
	
	
	
	
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		
		respostaService.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}/usuario")
	public ResponseEntity<EntityModel<UsuarioResponse>> usuarioByResposta(@PathVariable("id") Integer id) {
		
		Usuario usuario = respostaService.findById(id).getUsuario();
		
		UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);
		
		return ResponseEntity.ok(usuarioAssembler.toModel(response));
	}
	
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}/topico")
	public ResponseEntity<?> topicoByResposta(@PathVariable("id") Integer id) {
		
		Topico topico = respostaService.findById(id).getTopico();
		
		TopicoResponse response = modelMapper.map(topico, TopicoResponse.class);
		
		return ResponseEntity.ok(topicoAssembler.toModel(response));
	}
	
	
	

}
















