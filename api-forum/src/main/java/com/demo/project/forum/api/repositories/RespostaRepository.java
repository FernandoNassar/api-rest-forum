package com.demo.project.forum.api.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.forum.api.entities.Resposta;

@Repository
public interface RespostaRepository extends PagingAndSortingRepository<Resposta, Integer> {
	
	List<Resposta> findAll();
	
}
