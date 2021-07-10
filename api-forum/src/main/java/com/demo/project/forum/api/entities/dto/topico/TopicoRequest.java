package com.demo.project.forum.api.entities.dto.topico;

import com.demo.project.forum.api.entities.Topico;

public class TopicoRequest {
	
	private String titulo;
	private String pergunta;
	private Boolean respondido;
	
	public TopicoRequest() {}

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
	
	public Topico toEntity() {
		return update(new Topico());
	}
	
	public Topico update(Topico topico) {
		topico.setTitulo(titulo);
		topico.setPergunta(pergunta);
		topico.setRespondido(respondido);
		
		return topico;
	}
	
}
