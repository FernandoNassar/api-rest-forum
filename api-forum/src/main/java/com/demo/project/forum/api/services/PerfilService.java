package com.demo.project.forum.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.project.forum.api.entities.Role;
import com.demo.project.forum.api.repositories.PerfilRepository;

@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	public Role save(Role perfil) {
		return perfilRepository.save(perfil);
	}
	
	public Role findById(Integer id) {
		return perfilRepository.findById(id).get();
	}
	
}
