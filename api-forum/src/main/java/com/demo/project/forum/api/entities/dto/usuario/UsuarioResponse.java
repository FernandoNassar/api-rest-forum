package com.demo.project.forum.api.entities.dto.usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioResponse {
	
	private Integer id;
	private String nome;
	private String email;
	private List<String> roles = new ArrayList<>();
	
	
	public UsuarioResponse() {}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<String> getRoles() {
		return this.roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role) {
		this.roles.add(role);
	}
	
}
