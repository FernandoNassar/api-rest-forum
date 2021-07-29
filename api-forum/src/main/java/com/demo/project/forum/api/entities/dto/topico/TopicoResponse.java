package com.demo.project.forum.api.entities.dto.topico;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.project.forum.api.entities.Topico;

public class TopicoResponse {
	
	private Integer id;
	private String titulo;
	private String pergunta;
	private Boolean respondido;
	private TopicoUsuario usuario;
	private List<TopicoResposta> respostas; 
	
	public TopicoResponse() {}
	
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

	public List<TopicoResposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<TopicoResposta> resposta) {
		this.respostas = resposta;
	}

	public static Page<TopicoResponse> toDto(Page<Topico> topicos) {
		return topicos.map(TopicoResponse::new);
	}
	
}
