package com.demo.project.forum.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.forum.api.entities.Role;

@Repository
public interface PerfilRepository extends PagingAndSortingRepository<Role, Integer> {

}
