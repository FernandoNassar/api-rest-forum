package com.demo.project.forum.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.forum.api.entities.Usuario;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {
	
	Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
	
	Optional<Usuario> findByEmail(String email);
	
}
