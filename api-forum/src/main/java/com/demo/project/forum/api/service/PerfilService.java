package com.demo.project.forum.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.forum.api.model.Perfil;
import com.demo.project.forum.api.repository.PerfilRepository;

@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public Perfil save(Perfil perfil) {
		return perfilRepository.save(perfil);
	}
	
	public Perfil findById(Integer id) {
		return perfilRepository.findById(id).get();
	}
	
}
