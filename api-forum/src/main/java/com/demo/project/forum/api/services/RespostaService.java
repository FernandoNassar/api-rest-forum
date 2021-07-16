package com.demo.project.forum.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.forum.api.entities.Resposta;
import com.demo.project.forum.api.repositories.RespostaRepository;

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
	
	public List<Resposta> findAll() {
		return respostaRepository.findAll();
	}
}
