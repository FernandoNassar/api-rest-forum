package com.demo.project.forum.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.forum.api.model.Resposta;
import com.demo.project.forum.api.repository.RespostaRepository;

@Service
public class RespostaService {
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	public Resposta save(Resposta resposta) {
		return respostaRepository.save(resposta);
	}
	
	public Resposta findById(Integer id) {
		return respostaRepository.findById(id).get();
	}
	
}
