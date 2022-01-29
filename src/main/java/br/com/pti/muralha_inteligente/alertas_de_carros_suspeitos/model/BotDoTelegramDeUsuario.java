package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.BotDoTelegramRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;

@Entity
@Table(name="bots_do_telegram_de_usuarios")
public class BotDoTelegramDeUsuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=130)
	private String nomeDoUsuario;
	
	@Column(length=80)
	private String nomeDoBot;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonBackReference(value="bot-user-bot-movement")
	private BotDoTelegram botDoTelegram;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonBackReference(value="bot-user-user-movement")
	private Usuario usuario;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

	public BotDoTelegramDeUsuario() {}	

	public BotDoTelegramDeUsuario(Usuario usuario, BotDoTelegram botDoTelegram) {
		this.usuario=usuario;
		this.botDoTelegram=botDoTelegram;
		this.nomeDoBot=botDoTelegram.getDenominacao();
		this.nomeDoUsuario=usuario.getNomeDeUsuario();
		this.createdAt=LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}

	public void setNomeDoUsuario(String nomeDoUsuario) {
		this.nomeDoUsuario = nomeDoUsuario;
	}

	public String getNomeDoBot() {
		return nomeDoBot;
	}

	public void setNomeDoBot(String nomeDoBot) {
		this.nomeDoBot = nomeDoBot;
	}

	public BotDoTelegram getBotDoTelegram() {
		return botDoTelegram;
	}

	public void setBotDoTelegram(BotDoTelegram botDoTelegram) {
		this.botDoTelegram = botDoTelegram;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		return "BotDoTelegramDeUsuario [id=" + id + ", nomeDoUsuario=" + nomeDoUsuario + ", nomeDoBot=" + nomeDoBot
				+ "]";
	}
	
}
