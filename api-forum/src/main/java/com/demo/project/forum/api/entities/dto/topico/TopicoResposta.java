package com.demo.project.forum.api.entities.dto.topico;

public class TopicoResposta {
	
	private Integer id;
	private String texto;
	private TopicoUsuario usuario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public TopicoUsuario getTopicoUsuario() {
		return usuario;
	}
	public void setTopicoUsuario(TopicoUsuario usuario) {
		this.usuario = usuario;
	}
	
}
