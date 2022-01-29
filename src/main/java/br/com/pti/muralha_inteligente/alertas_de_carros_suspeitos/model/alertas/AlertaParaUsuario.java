package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroSuspeitoParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.Carro;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@MappedSuperclass
public abstract class AlertaParaUsuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuario;
	
	@Column(length=7)
	private String placa;
	
	@Column(length=130)
	private String nomeDeUsuario;
	
	private String latitude;
	
	private String longitude;
	
	private Boolean alertaEmitido;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	public AlertaParaUsuario() {}

	public AlertaParaUsuario(AlertaParaUsuarioForm alertaParaUsuarioForm, Usuario usuario,
			Carro carro) {
		this.usuario=usuario;
		this.placa=carro.getPlaca();
		this.longitude=alertaParaUsuarioForm.getLongitude();
		this.latitude=alertaParaUsuarioForm.getLatitude();
		this.nomeDeUsuario=usuario.getNomeDeUsuario();
		this.alertaEmitido=alertaParaUsuarioForm.getAlertaEmitido();
		this.createdAt=LocalDateTime.now();
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


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public Boolean getAlertaEmitido() {
		return alertaEmitido;
	}

	public void setAlertaEmitido(Boolean alerta_emitido) {
		this.alertaEmitido = alerta_emitido;
	}  
	
	@Override
	public String toString() {
		return "Alerta [id=" + id +", ocorrência="+"classe_filha"+ ", usuario=" + usuario + ", placa=" + placa + ", nomeDeUsuario=" + nomeDeUsuario
				+ ", latitude= "+latitude+", longitude= "+longitude+"]";
	}
	
	
}
