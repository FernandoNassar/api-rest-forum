package com.demo.project.forum.api.entities.dto.topico;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.demo.project.forum.api.entities.Topico;

public class TopicoRequest {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	@NotNull @NotEmpty @Length(min = 5)
	private String pergunta;
	private Boolean respondido;
	@NotNull
	private Integer usuarioID;
	
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

	public Integer getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Integer usuarioID) {
		this.usuarioID = usuarioID;
	}

	public Topico update(Topico topico) {
		topico.setTitulo(titulo);
		topico.setPergunta(pergunta);
		topico.setRespondido(respondido);
		
		return topico;
	}
	
}
