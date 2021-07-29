package com.demo.project.forum.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.project.forum.api.entities.Resposta;
import com.demo.project.forum.api.entities.Topico;
import com.demo.project.forum.api.entities.Usuario;
import com.demo.project.forum.api.exceptions.ObjectNotFoundException;
import com.demo.project.forum.api.repositories.TopicoRepository;

@Service
public class TopicoService {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	public Topico save(Topico topico) {
		return topicoRepository.save(topico);
	}
	
	public Topico findById(Integer id) {
		return topicoRepository.findById(id).orElseThrow(() -> {
			throw new ObjectNotFoundException("topic with id: " + id + " not found");
		});
	}
	
	public Page<Topico> findAll(Pageable pageable) {
		return topicoRepository.findAll(pageable);
	}
	
	public Page<Topico> findByTituloContaining(String titulo, Pageable pageable) {
		return topicoRepository.findByTituloContaining(titulo, pageable);
	}
	
	public Page<Usuario> findUsuarioByTopicoId(Integer id, Pageable pageable) {
		return null;
	}
	
	public Page<Resposta> findRespostasByTopico(Integer id, Pageable pageable) {
		return topicoRepository.findRespostasByTopico(id, pageable);
	}
	
}
