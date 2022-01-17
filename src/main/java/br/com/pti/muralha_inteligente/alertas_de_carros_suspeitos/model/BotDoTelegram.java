package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	private String id_do_chat;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	private Zona zona;

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

	public String getId_do_chat() {
		return id_do_chat;
	}

	public void setId_do_chat(String id_do_chat) {
		this.id_do_chat = id_do_chat;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	@Override
	public String toString() {
		return "BotDoTelegram [id=" + id + ", denominacao=" + denominacao + ", token=" + token + ", id_do_chat="
				+ id_do_chat + ", zona=" + zona + "]";
	}
}
