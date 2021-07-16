package com.demo.project.forum.api.entities.dto.resposta;

public class RespostaRequest {
	private Integer id;
	private String texto;
	private Integer usuarioID;
	private Integer topicoID;
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public void setUsuarioID(Integer usuarioID) {
		this.usuarioID = usuarioID;
	}
	public void setTopicoID(Integer topicoID) {
		this.topicoID = topicoID;
	}
	public Integer getId() {
		return id;
	}
	public String getTexto() {
		return texto;
	}
	public Integer getUsuarioID() {
		return usuarioID;
	}
	public Integer getTopicoID() {
		return topicoID;
	}
	
}
