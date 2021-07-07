package com.demo.project.forum.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.project.forum.api.model.Usuario;
import com.demo.project.forum.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario findById(Integer id) {
		return usuarioRepository.findById(id).orElseThrow(() -> {
			throw new RuntimeException("Erro");
		});
	}
	
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}
	
	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
	}
	
}
