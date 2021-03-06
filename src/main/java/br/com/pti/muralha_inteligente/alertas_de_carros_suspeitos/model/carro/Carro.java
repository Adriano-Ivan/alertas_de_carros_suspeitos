package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro;

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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@MappedSuperclass
public abstract class Carro {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=100)
	private String dono;
	
	@Column(length=7)
	private String placa;
	
	@Column(length=80)
	private String localDoAlerta;
	
	private LocalDateTime momentoDoAlerta=LocalDateTime.now();
	
	private Boolean alertado;

	private String nivelDeUrgencia;

	private String statusDoVeiculo;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private String latitude;
	
	private String longitude;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Zona zona;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuarioInsersor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario ultimoUsuarioEditor;
	
	public Carro() {}
	public Carro(CarroForm veiculoForm,Zona zona,Usuario usuarioInsersor) {
		this.dono=veiculoForm.getDono();
		this.placa=veiculoForm.getPlaca();
		this.localDoAlerta=veiculoForm.getLocalDoAlerta();
		this.momentoDoAlerta=veiculoForm.getMomentoDoAlerta();
		this.alertado=veiculoForm.getAlertado();
		this.nivelDeUrgencia=veiculoForm.getNivelDeUrgencia();
		this.statusDoVeiculo=veiculoForm.getStatusDoVeiculo();
		this.createdAt=LocalDateTime.now();
		this.usuarioInsersor=usuarioInsersor;
		this.zona=zona;
		
		if(veiculoForm.getLatitude()!=null) {
			this.latitude=veiculoForm.getLatitude();
		}
		if(veiculoForm.getLongitude()!=null) {
			this.longitude=veiculoForm.getLongitude();
		}
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUltimoUsuarioEditor() {
		return ultimoUsuarioEditor;
	}

	public void setUltimoUsuarioEditor(Usuario ultimoUsuarioEditor) {
		this.ultimoUsuarioEditor = ultimoUsuarioEditor;
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

	public String getNivelDeUrgencia() {
		return nivelDeUrgencia;
	}

	public void setNivelDeUrgencia(String nivelDeUrgencia) {
		this.nivelDeUrgencia = nivelDeUrgencia;
	}

	public String getStatusDoVeiculo() {
		return statusDoVeiculo;
	}

	public void setStatusDoVeiculo(String statusDoVeiculo) {
		this.statusDoVeiculo = statusDoVeiculo;
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
		return "Veiculo [id=" + id + ", dono=" + dono + ", placa=" + placa + ", localDoAlerta=" + localDoAlerta
				+ ", momentoDoAlerta=" + momentoDoAlerta + ", alertado=" + alertado + ", nivelDeUrgencia="
				+ nivelDeUrgencia + ", statusDoVeiculo=" + statusDoVeiculo + ", zona=" + getZona().toString() + ", usuarioInsersor="
				+ getUsuarioInsersor().toString() + ", ultimoUsuarioEditor=" + getUltimoUsuarioEditor().toString() + 
				", pr??prio_da_filha"+"]";
	}
	
	
}
