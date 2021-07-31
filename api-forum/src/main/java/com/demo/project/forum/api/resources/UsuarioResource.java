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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.project.forum.api.assembler.RespostaAssembler;
import com.demo.project.forum.api.assembler.TopicoAssembler;
import com.demo.project.forum.api.assembler.UsuarioAssembler;
import com.demo.project.forum.api.entities.Resposta;
import com.demo.project.forum.api.entities.Topico;
import com.demo.project.forum.api.entities.Usuario;
import com.demo.project.forum.api.entities.dto.resposta.RespostaResponse;
import com.demo.project.forum.api.entities.dto.topico.TopicoResponse;
import com.demo.project.forum.api.entities.dto.usuario.UsuarioRequest;
import com.demo.project.forum.api.entities.dto.usuario.UsuarioResponse;
import com.demo.project.forum.api.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioAssembler usuarioAssembler;
	
	@Autowired
	private TopicoAssembler topicoAssembler;
	
	@Autowired
	private RespostaAssembler respostaAssember;
	
	@Autowired
	private PagedResourcesAssembler<UsuarioResponse> usuarioResourcesAssembler;
	
	@Autowired
	private PagedResourcesAssembler<TopicoResponse> topicoResourcesAssembler;
	
	@Autowired
	private PagedResourcesAssembler<RespostaResponse> respostaResourcesAssembler;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@GetMapping
	public PagedModel<EntityModel<UsuarioResponse>> getAll(
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
			// ?page=0&size=10&sort=id,desc
		
		Page<Usuario> usuarios = usuarioService.findAll(pageable);
		
		Page<UsuarioResponse> response = usuarios
				.map(u -> modelMapper.map(u, UsuarioResponse.class));
		
		return usuarioResourcesAssembler.toModel(response, usuarioAssembler);
	}
	
	
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EntityModel<UsuarioResponse>> getById(@PathVariable(name = "id") Integer id) {
		
		Usuario usuario = usuarioService.findById(id);
		
		UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);
		
		return ResponseEntity.ok(usuarioAssembler.toModel(response));
	}
	
	
	
	
	
	@PostMapping
	public ResponseEntity<EntityModel<UsuarioResponse>> create(@RequestBody UsuarioRequest usuarioRequest, 
			UriComponentsBuilder uriBuilder) {
		
//		Usuario usuario = usuarioService.save(usuarioRequest.toEntity());
		Usuario usuario = usuarioService.create(usuarioRequest.toEntity());
		
		UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);
		
		EntityModel<UsuarioResponse> entityModel = usuarioAssembler.toModel(response);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
	
	
	
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EntityModel<UsuarioResponse>> update(@PathVariable(name = "id") Integer id,
			@RequestBody UsuarioRequest usuarioRequest) {
		
		Usuario usuario = usuarioService.findById(id);
		
		usuario = usuarioRequest.update(usuario);
		
		UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);
		
		return ResponseEntity.ok(usuarioAssembler.toModel(response));
	}
	
	
	
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "usuarioID") Integer id) {
		
		usuarioService.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	@GetMapping("/{id}/topicos")
	public ResponseEntity<PagedModel<EntityModel<TopicoResponse>>> topicosByUsuario(
			@PathVariable("id") Integer id, 
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
		
		Page<Topico> topicos = usuarioService.findTopicoByUsuarioId(id, pageable);
		
		Page<TopicoResponse> response = topicos.map(t -> modelMapper.map(t, TopicoResponse.class));
		
		return ResponseEntity.ok(topicoResourcesAssembler.toModel(response, topicoAssembler));	
	}
	
	
	
	
	
	@GetMapping("/{id}/respostas")
	public ResponseEntity<PagedModel<EntityModel<RespostaResponse>>> respostasByUsuario(
			@PathVariable("id") Integer id, 
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
		
		Page<Resposta> respostas = usuarioService.findRespostaByUsuarioId(id, pageable);
		
		Page<RespostaResponse> response = respostas.map(r -> modelMapper.map(r, RespostaResponse.class));
		
		return ResponseEntity.ok(respostaResourcesAssembler.toModel(response, respostaAssember));
		
	}
	
	
	
	
	
	@GetMapping(value = "/search")
	public ResponseEntity<?> search(@RequestParam(required = false) String nome, 
			@RequestParam(required = false) String email,
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
		
		if(nome != null) {
			Page<Usuario> usuarios = usuarioService.findByNomeContaining(nome, pageable);
			Page<UsuarioResponse> response = usuarios.map(usuario -> modelMapper.map(usuarios, UsuarioResponse.class));
			return ResponseEntity.ok(usuarioResourcesAssembler.toModel(response, usuarioAssembler));
		}
		
		if(email != null) {
			Usuario usuario = usuarioService.findByEmail(email);
			UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);
			return ResponseEntity.ok(usuarioAssembler.toModel(response));
		}
		
		return ResponseEntity.noContent().build();	
	}
	
	
}
