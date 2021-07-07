package com.demo.project.forum.api.model.dto.usuario;

import com.demo.project.forum.api.model.Usuario;

public class UsuarioRequest {
	
	private String nome;
	private String email;
	private String senha;
	
	public UsuarioRequest() {}
	
	public UsuarioRequest(String nome, String email, String senha) {
		this.nome = nome; this.email = email; this.senha = senha;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario toEntity() {
		Usuario usuario = new Usuario();
		return update(usuario);
	}
	
	public Usuario update(Usuario usuario) {
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		return usuario;
	}
	
}
