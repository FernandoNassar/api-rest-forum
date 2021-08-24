package com.demo.project.forum.api.entities.dto.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.demo.project.forum.api.entities.Usuario;

public class UsuarioRequest {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull @NotEmpty @Email
	private String email;
	@NotNull @NotEmpty @Length(min = 5)
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
