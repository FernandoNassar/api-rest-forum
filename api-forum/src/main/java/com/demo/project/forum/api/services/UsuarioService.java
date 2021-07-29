package com.demo.project.forum.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.project.forum.api.entities.Resposta;
import com.demo.project.forum.api.entities.Topico;
import com.demo.project.forum.api.entities.Usuario;
import com.demo.project.forum.api.exceptions.ObjectNotFoundException;
import com.demo.project.forum.api.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario findById(Integer id) {
		return usuarioRepository.findById(id).orElseThrow(() -> {
			throw new ObjectNotFoundException("User with id: " + id + " not found");
		});
	}
	
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}
	
	public void deleteById(Integer id) {
		try { usuarioRepository.deleteById(id); }
		
		catch(EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("User with id: " + id + " not found");
		}
	}
	
	public Page<Usuario> findByNomeContaining(String nome, Pageable pageable) {
		return usuarioRepository.findByNomeContaining(nome, pageable);
	}
	
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email).orElseThrow(() -> {
			throw new ObjectNotFoundException("User with email: " + email + " not found");
		});
	}
	
	public Page<Topico> findTopicoByUsuarioId(Integer id, Pageable pageable) {
		return usuarioRepository.findTopicosByUsuarioId(id, pageable);
	}
	
	public Page<Resposta> findRespostaByUsuarioId(Integer id, Pageable pageable) {
		return usuarioRepository.findRespostaByUsuarioId(id, pageable);
	}
	
}









