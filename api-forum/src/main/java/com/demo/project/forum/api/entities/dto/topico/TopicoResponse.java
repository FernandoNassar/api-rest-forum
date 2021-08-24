package com.demo.project.forum.api.entities.dto.topico;

public class TopicoResponse {
	
	private Integer id;
	private String titulo;
	private String pergunta;
	private Boolean respondido;
	private TopicoUsuario usuario;
	
	public TopicoResponse() {}


	public Integer getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getPergunta() {
		return pergunta;
	}

	public Boolean getRespondido() {
		return respondido;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public void setRespondido(Boolean respondido) {
		this.respondido = respondido;
	}

	public TopicoUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TopicoUsuario usuario) {
		this.usuario = usuario;
	}
	
}
