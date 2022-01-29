package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.BotDoTelegramDeUsuario;

public class BotDoTelegramDeUsuarioDto {

	private Long id;
	private Long idDoUsuario;
	private Long idDoBotDoTelegram;
	private String nomeDoUsuario;
	private String denominacaoDoBotDoTelegram;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public BotDoTelegramDeUsuarioDto(BotDoTelegramDeUsuario botDoTelegramDeUsuario) {
		this.id=botDoTelegramDeUsuario.getId();
		this.idDoBotDoTelegram=botDoTelegramDeUsuario.getBotDoTelegram().getId();
		this.idDoUsuario=botDoTelegramDeUsuario.getUsuario().getId();
		this.nomeDoUsuario=botDoTelegramDeUsuario.getNomeDoUsuario();
		this.denominacaoDoBotDoTelegram=botDoTelegramDeUsuario.getNomeDoBot();
		
		if(botDoTelegramDeUsuario.getCreatedAt()!=null) {
			this.createdAt=botDoTelegramDeUsuario.getCreatedAt();
		}
		if(botDoTelegramDeUsuario.getUpdatedAt()!=null) {
			this.updatedAt=botDoTelegramDeUsuario.getUpdatedAt();
		}
	}

	public Long getId() {
		return id;
	}

	public Long getIdDoUsuario() {
		return idDoUsuario;
	}

	public Long getIdDoBotDoTelegram() {
		return idDoBotDoTelegram;
	}

	public String getNomeDeUsuario() {
		return nomeDoUsuario;
	}

	public String getDenominacaoDoBotDoTelegram() {
		return denominacaoDoBotDoTelegram;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
