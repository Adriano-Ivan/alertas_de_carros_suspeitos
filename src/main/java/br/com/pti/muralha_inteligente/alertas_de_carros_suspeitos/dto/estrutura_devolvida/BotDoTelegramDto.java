package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.BotDoTelegram;

public class BotDoTelegramDto {

	private Long id;
	private String denominacao;
	private String token;
	private String idDoChat;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long idDaZona;
	
	public BotDoTelegramDto(BotDoTelegram botDoTelegram) {
		this.id=botDoTelegram.getId();
		this.denominacao=botDoTelegram.getDenominacao();
		this.token=botDoTelegram.getToken();
		this.idDoChat=botDoTelegram.getIdDoChat();
		
		if(botDoTelegram.getCreatedAt()!=null) {
			this.createdAt=botDoTelegram.getCreatedAt();
		}
		if(botDoTelegram.getUpdatedAt()!=null) {
			this.updatedAt=botDoTelegram.getUpdatedAt();
		}
		if(botDoTelegram.getZona()!=null) {
			this.idDaZona=botDoTelegram.getZona().getId();
		}
	}

	public Long getId() {
		return id;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public String getToken() {
		return token;
	}

	public String getIdDoChat() {
		return idDoChat;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public Long getIdZona() {
		return idDaZona;
	}
}
