package com.demo.project.forum.api.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
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

import com.demo.project.forum.api.assembler.UsuarioAssembler;
import com.demo.project.forum.api.model.Usuario;
import com.demo.project.forum.api.model.dto.usuario.UsuarioRequest;
import com.demo.project.forum.api.model.dto.usuario.UsuarioResponse;
import com.demo.project.forum.api.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioAssembler usuarioAssembler;
	
	@Autowired
	private PagedResourcesAssembler<UsuarioResponse> usuarioResourcesAssembler;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping
	public PagedModel<EntityModel<UsuarioResponse>> getAll(
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
			// ?page=0&size=10&sort=id,desc
		
		Page<UsuarioResponse> response = UsuarioResponse.toDto(usuarioService.findAll(pageable));
		
		return usuarioResourcesAssembler.toModel(response, usuarioAssembler);
	}
	
	@GetMapping(value = "/{usuarioID}")
	public ResponseEntity<EntityModel<UsuarioResponse>> getById(@PathVariable(name = "usuarioID") Integer usuarioID) {
		
		Usuario usuario = usuarioService.findById(usuarioID);
		UsuarioResponse response = new UsuarioResponse(usuario);
		
		return ResponseEntity.ok(usuarioAssembler.toModel(response));
	}
	
	@PostMapping
	public ResponseEntity<EntityModel<UsuarioResponse>> create(@RequestBody UsuarioRequest usuarioRequest, 
			UriComponentsBuilder uriBuilder) {
		
		Usuario usuario = usuarioService.save(usuarioRequest.toEntity());
		UsuarioResponse response = new UsuarioResponse(usuario);
		
		URI uri = uriBuilder.path("/usuarios/{usuarioID}").buildAndExpand(response.getId()).toUri();
		
		return ResponseEntity.created(uri).body(usuarioAssembler.toModel(response));
	}
	
	@PutMapping(value = "/{usuarioID}")
	public ResponseEntity<EntityModel<UsuarioResponse>> update(@PathVariable(name = "usuarioID") Integer usuarioID,
			@RequestBody UsuarioRequest usuarioRequest) {
		
		Usuario usuario = usuarioService.findById(usuarioID);
		usuario = usuarioRequest.update(usuario);
		UsuarioResponse response = new UsuarioResponse(usuario);
		
		return ResponseEntity.ok(usuarioAssembler.toModel(response));
	}
	
	@DeleteMapping(value = "/{usuarioID}")
	public ResponseEntity<Void> delete(@PathVariable(name = "usuarioID") Integer usuarioID) {
		
		usuarioService.deleteById(usuarioID);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<?> search(@RequestParam(required = false) String nome, @RequestParam(required = false) String email, Pageable pageable) {
		
		if(nome != null) {
			Page<UsuarioResponse> response = UsuarioResponse.toDto(usuarioService.findByNomeContaining(nome, pageable));
			return ResponseEntity.ok(usuarioResourcesAssembler.toModel(response, usuarioAssembler));
		}
		
		if(email != null) {
			UsuarioResponse response = new UsuarioResponse(usuarioService.findByEmail(email));
			return ResponseEntity.ok(usuarioAssembler.toModel(response));
		}
		
		return ResponseEntity.noContent().build();	
	}
	
	
}
