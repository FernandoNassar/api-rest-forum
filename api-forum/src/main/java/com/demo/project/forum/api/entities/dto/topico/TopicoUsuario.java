package com.demo.project.forum.api.entities.dto.topico;


public class TopicoUsuario {
	
	private Integer id;
	private String nome;
	private String email;
	
	public TopicoUsuario() {}
	
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
