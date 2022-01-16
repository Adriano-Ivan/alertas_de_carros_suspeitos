package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo.NivelDeUrgencia;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo.StatusDoVeiculo;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@MappedSuperclass
public abstract class Veiculo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	@Column(length=100)
	protected String dono;
	
	@Column(length=7)
	protected String placa;
	
	@Column(length=80)
	protected String localDoAlerta;
	
	protected LocalDateTime momentoDoAlerta;
	
	protected Boolean alertado;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonBackReference
	protected NivelDeUrgencia nivelDeUrgencia;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	protected StatusDoVeiculo statusDoVeiculo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	protected Zona zona;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	protected Usuario usuarioInsersor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	protected Usuario ultimoUsuarioEditor;
 
	public Usuario getUltimoUsuarioEditor() {
		return ultimoUsuarioEditor;
	}

	public void setUltimoUsuarioEditor(Usuario ultimoUsuarioEditor) {
		this.ultimoUsuarioEditor = ultimoUsuarioEditor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDono() {
		return dono;
	}

	public void setDono(String dono) {
		this.dono = dono;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getLocalDoAlerta() {
		return localDoAlerta;
	}

	public void setLocalDoAlerta(String localDoAlerta) {
		this.localDoAlerta = localDoAlerta;
	}

	public LocalDateTime getMomentoDoAlerta() {
		return momentoDoAlerta;
	}

	public void setMomentoDoAlerta(LocalDateTime momentoDoAlerta) {
		this.momentoDoAlerta = momentoDoAlerta;
	}

	public Boolean getAlertado() {
		return alertado;
	}

	public void setAlertado(Boolean alertado) {
		this.alertado = alertado;
	}

	public NivelDeUrgencia getNivelDeUrgencia() {
		return nivelDeUrgencia;
	}

	public void setNivelDeUrgencia(NivelDeUrgencia nivelDeUrgencia) {
		this.nivelDeUrgencia = nivelDeUrgencia;
	}

	public StatusDoVeiculo getStatusDoVeiculo() {
		return statusDoVeiculo;
	}

	public void setStatusDoVeiculo(StatusDoVeiculo statusDoVeiculo) {
		this.statusDoVeiculo = statusDoVeiculo;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Usuario getUsuarioInsersor() {
		return usuarioInsersor;
	}

	public void setUsuarioInsersor(Usuario usuarioInsersor) {
		this.usuarioInsersor = usuarioInsersor;
	}
	
	
}
