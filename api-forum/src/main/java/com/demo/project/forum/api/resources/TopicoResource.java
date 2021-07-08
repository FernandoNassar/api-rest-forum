package com.demo.project.forum.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.forum.api.entities.dto.topico.TopicoResponse;
import com.demo.project.forum.api.services.TopicoService;

@RestController
@RequestMapping("/topicos")
public class TopicoResource {
	
	@Autowired
	private TopicoService topicoService;
	
	@GetMapping
	public ResponseEntity<Page<TopicoResponse>> getAll(
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
		
		Page<TopicoResponse> response = TopicoResponse.toDto(topicoService.findAll(pageable));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoResponse> getById(@PathVariable("id") Integer id) {
		
		TopicoResponse response = new TopicoResponse(topicoService.findById(id));
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(required = false) String titulo,
			@PageableDefault(sort = "id", size = 10, direction = Direction.ASC) Pageable pageable) {
		
		if(titulo != null) {
			Page<TopicoResponse> response = TopicoResponse.toDto(topicoService.findByTituloContaining(titulo, pageable));
			return ResponseEntity.ok(response);
		}
		
		return ResponseEntity.noContent().build();
	}
}







