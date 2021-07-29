package com.demo.project.forum.api.resources;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.forum.api.assembler.RespostaAssembler;
import com.demo.project.forum.api.assembler.TopicoAssembler;
import com.demo.project.forum.api.assembler.UsuarioAssembler;
import com.demo.project.forum.api.entities.Resposta;
import com.demo.project.forum.api.entities.Topico;
import com.demo.project.forum.api.entities.Usuario;
import com.demo.project.forum.api.entities.dto.resposta.RespostaResponse;
import com.demo.project.forum.api.entities.dto.topico.TopicoRequest;
import com.demo.project.forum.api.entities.dto.topico.TopicoResponse;
import com.demo.project.forum.api.entities.dto.usuario.UsuarioResponse;
import com.demo.project.forum.api.services.TopicoService;
import com.demo.project.forum.api.services.UsuarioService;

@RestController
@RequestMapping("/topicos")
public class TopicoResource {
	
	@Autowired
	private TopicoService topicoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TopicoAssembler topicoAssembler;
	
	@Autowired
	private UsuarioAssembler usuarioAssembler;
	
	@Autowired
	private RespostaAssembler respostaAssembler;
	
	@Autowired
	private PagedResourcesAssembler<RespostaResponse> respostaResourcesAssembler;
	
	@Autowired
	private PagedResourcesAssembler<TopicoResponse> topicoResourcesAssembler;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	
	
	@GetMapping
	public ResponseEntity<PagedModel<EntityModel<TopicoResponse>>> getAll(
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
		
		Page<Topico> topicos = topicoService.findAll(pageable);
		
		Page<TopicoResponse> response = topicos.map(t -> modelMapper.map(t, TopicoResponse.class));
			
		return ResponseEntity.ok(topicoResourcesAssembler.toModel(response, topicoAssembler));		
	}
	
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<TopicoResponse>> getById(@PathVariable("id") Integer id) {
		
		Topico topico = topicoService.findById(id);
		
		TopicoResponse response = modelMapper.map(topico, TopicoResponse.class);
		
		HttpHeaders header = new HttpHeaders();
		
		header.add("Allow", "GET, POST, PUT, DELETE");
		
		return new ResponseEntity<EntityModel<TopicoResponse>>(topicoAssembler.toModel(response), header, HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(required = false) String titulo,
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
		
		if(titulo != null) {
			Page<Topico> topicos = topicoService.findByTituloContaining(titulo, pageable);
			Page<TopicoResponse> response = topicos.map(t -> modelMapper.map(t, TopicoResponse.class));
			
			return ResponseEntity.ok(topicoResourcesAssembler.toModel(response, topicoAssembler));
		}	
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	@PostMapping
	public ResponseEntity<EntityModel<TopicoResponse>> create(@RequestBody TopicoRequest topicoRequest) {
		
		Topico topico = modelMapper.map(topicoRequest, Topico.class);
		topico.setUsuario(usuarioService.findById(topicoRequest.getUsuarioID()));
		topico = topicoService.save(topico);
		
		TopicoResponse response = modelMapper.map(topico, TopicoResponse.class);
		
		EntityModel<TopicoResponse> entityModel = topicoAssembler.toModel(response);

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);		
	}
	
	
	
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EntityModel<TopicoResponse>> update(@PathVariable(name = "id") Integer id, 
			@RequestBody TopicoRequest topicoRequest) {
		
		Topico topico = topicoRequest.update(topicoService.findById(id));
		
		topico = topicoService.save(topico);
		
		TopicoResponse response = modelMapper.map(topico, TopicoResponse.class);
		
		EntityModel<TopicoResponse> entityModel = topicoAssembler.toModel(response);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	
	
	
	
	@GetMapping("/{id}/usuario")
	public ResponseEntity<?> usuarioByTopico(
			@PathVariable(name="id") Integer id, 
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
		
		Usuario usuario = topicoService.findById(id).getUsuario();
		
		UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);
		
		return ResponseEntity.ok(usuarioAssembler.toModel(response));
	}
	
	
	
	
	
	@GetMapping("/{id}/respostas")
	public ResponseEntity<?> respostasByTopico(
			@PathVariable(name="id") Integer id, 
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
		
		Page<Resposta> respostas = topicoService.findRespostasByTopico(id, pageable);
		
		Page<RespostaResponse> response = respostas.map(r -> modelMapper.map(r, RespostaResponse.class));
		
		return ResponseEntity.ok(respostaResourcesAssembler.toModel(response, respostaAssembler));
		
		
	}
	
	
}







