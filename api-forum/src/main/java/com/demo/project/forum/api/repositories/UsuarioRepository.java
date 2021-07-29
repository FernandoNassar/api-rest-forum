package com.demo.project.forum.api.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.forum.api.entities.Resposta;
import com.demo.project.forum.api.entities.Topico;
import com.demo.project.forum.api.entities.Usuario;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {
	
	Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
	
	Optional<Usuario> findByEmail(String email);
	
	@Query(value = "SELECT t FROM Topico t WHERE t.usuario.id = :id")
	Page<Topico> findTopicosByUsuarioId(Integer id, Pageable pageable);
	
	@Query(value = "SELECT r FROM Resposta r WHERE r.usuario.id = :id")
	Page<Resposta> findRespostaByUsuarioId(Integer id, Pageable pageable);
}
