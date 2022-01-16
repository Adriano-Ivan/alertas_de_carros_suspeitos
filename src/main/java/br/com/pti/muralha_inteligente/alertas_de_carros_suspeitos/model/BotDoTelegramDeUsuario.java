package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

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
	
	@ManyToOne
	private BotDoTelegram botDoTelegram;
	
	@ManyToOne
	private Usuario usuario;

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
}