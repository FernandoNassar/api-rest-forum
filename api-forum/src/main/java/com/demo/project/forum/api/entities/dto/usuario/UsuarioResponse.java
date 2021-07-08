package com.demo.project.forum.api.entities.dto.usuario;

import org.springframework.data.domain.Page;

import com.demo.project.forum.api.entities.Usuario;

public class UsuarioResponse {
	
	private Integer id;
	private String nome;
	private String email;
	
	public UsuarioResponse(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	
	public static Page<UsuarioResponse> toDto(Page<Usuario> usuarios) {
		return usuarios.map(UsuarioResponse::new);
	}
}
