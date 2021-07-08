package com.demo.project.forum.api.entities.dto.topico;

import org.springframework.data.domain.Page;

import com.demo.project.forum.api.entities.Topico;

public class TopicoResponse {
	
	private Integer id;
	private String titulo;
	private String pergunta;
	private Boolean respondido;
	
	
	public TopicoResponse(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.pergunta = topico.getPergunta();
		this.respondido = topico.getRespondido();
	}


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
	
	public static Page<TopicoResponse> toDto(Page<Topico> topicos) {
		return topicos.map(TopicoResponse::new);
	}
	
}
