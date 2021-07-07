package com.demo.project.forum.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.forum.api.model.Perfil;

@Repository
public interface PerfilRepository extends PagingAndSortingRepository<Perfil, Integer> {

}
