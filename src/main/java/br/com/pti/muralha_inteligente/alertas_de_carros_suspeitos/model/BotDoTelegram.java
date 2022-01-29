package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.BotDoTelegramDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.BotDoTelegramForm;

@Entity
@Table(name="bots_do_telegram")
public class BotDoTelegram {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=80)
	private String denominacao;
	
	@Column(length=256)
	private String token;
	
	@Column(length=256)
	private String idDoChat;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	private Zona zona;
	
	@OneToMany(mappedBy="botDoTelegram",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonManagedReference(value="bot-user-bot-movement")
	private List<BotDoTelegramDeUsuario> botsDoTelegramDeUsuarios;
	
	public BotDoTelegram() {}
	
	public BotDoTelegram(BotDoTelegramForm botDoTelegramForm, Zona zona) {
		this.denominacao=botDoTelegramForm.getDenominacao();
		this.token=botDoTelegramForm.getToken();
		this.idDoChat=botDoTelegramForm.getIdDoChat();
		this.zona=zona;
		this.createdAt=LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIdDoChat() {
		return idDoChat;
	}

	public void setIdDoChat(String idDoChat) {
		this.idDoChat = idDoChat;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}
 
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "BotDoTelegram [id=" + id + ", denominacao=" + denominacao + ", token=" + token + ", id_do_chat="
				+ idDoChat + ", zona=" + zona + "]";
	}

	public static Page<BotDoTelegramDto> converter(Page<BotDoTelegram> botsDoTelegram) {
		return botsDoTelegram.map(BotDoTelegramDto::new);
	}
	
	public static BotDoTelegramDto converter(BotDoTelegram botDoTelegram) {
		return new BotDoTelegramDto(botDoTelegram);
	}
}
