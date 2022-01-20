package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.mensagem;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.Mensagem;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemEnviada;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

public class MensagemDto {
	protected  String mensagem;
	
	protected Long idUsuario;
	
	protected LocalDateTime updatedAt;
	
	protected LocalDateTime createdAt;

	public MensagemDto() {}
	public MensagemDto(Mensagem mensagemEnt) {
		this.mensagem=mensagemEnt.getMensagem();
		this.createdAt=mensagemEnt.getCreatedAt();
		this.updatedAt=mensagemEnt.getUpdatedAt();
		this.idUsuario=mensagemEnt.getUsuario().getId();
	}

	public String getMensagem() {
		return mensagem;
	}

	public Long getUsuario() {
		return idUsuario;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	
}
