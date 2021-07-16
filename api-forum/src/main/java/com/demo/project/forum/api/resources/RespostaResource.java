package com.demo.project.forum.api.resources;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.forum.api.entities.Resposta;
import com.demo.project.forum.api.entities.dto.resposta.RespostaResponse;
import com.demo.project.forum.api.services.RespostaService;

@RestController
@RequestMapping(path = "/respostas")
public class RespostaResource {

	@Autowired
	private RespostaService respostaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<RespostaResponse> getById(@PathVariable("id") Integer id) {
		
		Resposta resposta = respostaService.findById(id);
		
		RespostaResponse response = modelMapper.map(resposta, RespostaResponse.class);
		
		return ResponseEntity.ok(response);
	}
	
	

}
















