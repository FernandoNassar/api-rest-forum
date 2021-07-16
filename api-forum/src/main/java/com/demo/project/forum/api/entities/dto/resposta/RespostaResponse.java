package com.demo.project.forum.api.entities.dto.resposta;

public class RespostaResponse {
	
	private Integer id;
	private String texto;
	private RespostaUsuario usuario;
	private TopicoResponse topico;
	
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
	public RespostaUsuario getUsuario() {
		return usuario;
	}
	public void setUsuario(RespostaUsuario usuario) {
		this.usuario = usuario;
	}
	public TopicoResponse getTopico() {
		return topico;
	}
	public void setTopico(TopicoResponse topico) {
		this.topico = topico;
	}

}
