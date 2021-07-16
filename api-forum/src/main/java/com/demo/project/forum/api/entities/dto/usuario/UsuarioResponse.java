package com.demo.project.forum.api.entities.dto.usuario;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.project.forum.api.entities.Usuario;

public class UsuarioResponse {
	
	private Integer id;
	private String nome;
	private String email;
	private List<UsuarioResposta> respostas;
	
	
	public UsuarioResponse() {}
	
	public UsuarioResponse(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	
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

	public List<UsuarioResposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<UsuarioResposta> respostas) {
		this.respostas = respostas;
	}

	public static Page<UsuarioResponse> toDto(Page<Usuario> usuarios) {
		return usuarios.map(UsuarioResponse::new);
	}
	
}
