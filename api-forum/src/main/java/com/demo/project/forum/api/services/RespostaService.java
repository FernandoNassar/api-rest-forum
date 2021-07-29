package com.demo.project.forum.api.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.project.forum.api.entities.Resposta;
import com.demo.project.forum.api.entities.dto.resposta.RespostaRequest;
import com.demo.project.forum.api.exceptions.ObjectNotFoundException;
import com.demo.project.forum.api.repositories.RespostaRepository;

@Service
public class RespostaService {
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	public Resposta save(Resposta resposta) {
		return respostaRepository.save(resposta);
	}
	
	
	
	public Resposta findById(Integer id) {
		return respostaRepository
				.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("resposta with id: " + id + " not found"));
	}
	
	
	
	public List<Resposta> findAll() {
		return respostaRepository.findAll();
	}
	
	
	
	public Page<Resposta> findAll(Pageable pageable) {
		return respostaRepository.findAll(pageable);
	}
	
	
	
	public void deleteById(Integer id) {
		
		try { respostaRepository.deleteById(id); }
		
		catch(EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("resposta with id: " + id + " not found");
		}
		
	}
	
	
	
	public Resposta update(Integer id, RespostaRequest request) {
		
		Resposta resposta = respostaRepository
				.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("resposta with id: " + id + " not found"));
		
		resposta = modelMapper.map(request, Resposta.class);
		resposta.setId(id);
		
		return respostaRepository.save(resposta);
	}
}
