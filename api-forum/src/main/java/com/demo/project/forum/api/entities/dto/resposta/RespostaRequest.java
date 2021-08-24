package com.demo.project.forum.api.entities.dto.resposta;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public class RespostaRequest {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String texto;
	@NotNull
	private Integer usuarioID;
	@NotNull
	private Integer topicoID;
	
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public void setUsuarioID(Integer usuarioID) {
		this.usuarioID = usuarioID;
	}
	public void setTopicoID(Integer topicoID) {
		this.topicoID = topicoID;
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
