package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.mensagem;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.Mensagem;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemEnviada;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

public class MensagemDto {
	
	private Long id;
	
	private  String mensagem;
	
	private Long idUsuario;
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime createdAt;

	public MensagemDto() {}
	public MensagemDto(Mensagem mensagemEnt) {
		this.id=mensagemEnt.getId();
		this.mensagem=mensagemEnt.getMensagem();
		
		if(mensagemEnt.getCreatedAt()!=null) {
			this.createdAt=mensagemEnt.getCreatedAt();
		}
		if(mensagemEnt.getUpdatedAt()!=null) {
			this.updatedAt=mensagemEnt.getUpdatedAt();
		}
		if(mensagemEnt.getUsuario()!=null) {
			this.idUsuario=mensagemEnt.getUsuario().getId();
		}
	}

	public Long getId() {
		return id;
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
