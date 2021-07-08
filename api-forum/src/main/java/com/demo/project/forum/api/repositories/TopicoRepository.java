package com.demo.project.forum.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.forum.api.entities.Topico;

@Repository
public interface TopicoRepository extends PagingAndSortingRepository<Topico, Integer> {

	Page<Topico> findByTituloContaining(String titulo, Pageable pageable);

}
