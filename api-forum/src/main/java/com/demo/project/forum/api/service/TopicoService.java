package com.demo.project.forum.api.service;

import org.springframework.stereotype.Service;

import com.demo.project.forum.api.model.Topico;
import com.demo.project.forum.api.repository.TopicoRepository;

@Service
public class TopicoService {
	
	private TopicoRepository topicoRepository;
	
	public Topico save(Topico topico) {
		return topicoRepository.save(topico);
	}
	
	public Topico findById(Integer id) {
		return topicoRepository.findById(id).get();
	}
	
}
