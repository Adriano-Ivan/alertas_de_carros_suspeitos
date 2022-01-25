package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.BotDoTelegram;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.BotDoTelegramRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class BotDoTelegramForm {

	@NotNull @NotBlank @Size(min=4)
	private String denominacao;
	
	@NotNull @NotBlank
	private String idDoChat;
	
	@NotNull @NotBlank
	private String token;
	
	@NotNull
	private Long idZona;

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	public String getIdDoChat() {
		return idDoChat;
	}

	public void setIdDoChat(String idDoChat) {
		this.idDoChat = idDoChat;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getIdZona() {
		return idZona;
	}

	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}
	
	private Zona definirZona(ZonaRepository zonaRepository) {
		Optional<Zona> zonaOpt = zonaRepository.findById(idZona);
		Zona zona = zonaOpt.orElse(null);
		return zona;
	}
	public BotDoTelegram converter(ZonaRepository zonaRepository) {
		Zona zona = definirZona(zonaRepository);
		
		return new BotDoTelegram(this,zona);
	}
	
	public BotDoTelegram atualizar(Long id,BotDoTelegramRepository botDoTelegramRepository,
			ZonaRepository zonaRepository) {
		BotDoTelegram botDoTelegram = botDoTelegramRepository.getById(id);
		Zona zona = definirZona(zonaRepository);
		
		botDoTelegram.setDenominacao(denominacao);
		botDoTelegram.setIdDoChat(idDoChat);
		botDoTelegram.setToken(token);
		botDoTelegram.setZona(zona);
		botDoTelegram.setUpdatedAt(LocalDateTime.now());
		
		return botDoTelegram;
	}
}
