package com.demo.project.forum.api.entities.dto.resposta;

public class TopicoResponse {
	
	public Integer id;
	public String titulo;
	public String pergunta;
	public Boolean respondido;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public Boolean getRespondido() {
		return respondido;
	}
	public void setRespondido(Boolean respondido) {
		this.respondido = respondido;
	}

}
