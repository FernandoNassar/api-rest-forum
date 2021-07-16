package com.demo.project.forum.api.resources;

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

import com.demo.project.forum.api.assembler.TopicoAssembler;
import com.demo.project.forum.api.entities.Topico;
import com.demo.project.forum.api.entities.dto.topico.TopicoRequest;
import com.demo.project.forum.api.entities.dto.topico.TopicoResponse;
import com.demo.project.forum.api.services.TopicoService;

@RestController
@RequestMapping("/topicos")
public class TopicoResource {
	
	@Autowired
	private TopicoService topicoService;
	
	@Autowired
	private TopicoAssembler topicoAssembler;
	
	@Autowired
	private PagedResourcesAssembler<TopicoResponse> topicoResourcesAssembler;
	
	
	
	@GetMapping
	public ResponseEntity<PagedModel<EntityModel<TopicoResponse>>> getAll(
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
		
		Page<TopicoResponse> response = TopicoResponse.toDto(topicoService.findAll(pageable));
			
		return ResponseEntity.ok(topicoResourcesAssembler.toModel(response, topicoAssembler));		
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<TopicoResponse>> getById(@PathVariable("id") Integer id) {
		
		TopicoResponse response = new TopicoResponse(topicoService.findById(id));
		
		HttpHeaders header = new HttpHeaders();
		
		header.add("Allow", "GET, POST, PUT, DELETE");
		
		return new ResponseEntity<EntityModel<TopicoResponse>>(topicoAssembler.toModel(response), header, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(required = false) String titulo,
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
		
		if(titulo != null) {
			Page<Topico> topicos = topicoService.findByTituloContaining(titulo, pageable);
			Page<TopicoResponse> response = TopicoResponse.toDto(topicos);	
			
			return ResponseEntity.ok(topicoResourcesAssembler.toModel(response, topicoAssembler));
		}	
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	@PostMapping
	public ResponseEntity<EntityModel<TopicoResponse>> create(@RequestBody TopicoRequest topicoRequest) {
		
		Topico topico = topicoService.save(topicoRequest.toEntity());
		
		TopicoResponse response = new TopicoResponse(topico);
		
		EntityModel<TopicoResponse> entityModel = topicoAssembler.toModel(response);

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);		
	}
	
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EntityModel<TopicoResponse>> update(@PathVariable(name = "id") Integer id, 
			@RequestBody TopicoRequest topicoRequest) {
		
		Topico topico = topicoRequest.update(topicoService.findById(id));
		
		TopicoResponse response = new TopicoResponse(topicoService.save(topico));
		
		EntityModel<TopicoResponse> entityModel = topicoAssembler.toModel(response);
		
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

}







