package com.demo.project.forum.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.forum.api.entities.Perfil;
import com.demo.project.forum.api.repositories.PerfilRepository;

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
