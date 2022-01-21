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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
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
	
	protected LocalDateTime momentoDoAlerta=LocalDateTime.now();
	
	protected Boolean alertado;

	protected String nivelDeUrgencia;

	protected String statusDoVeiculo;
	
	protected LocalDateTime createdAt;
	
	protected LocalDateTime updatedAt;
	
	public Veiculo() {}
	public Veiculo(VeiculoForm veiculoForm,Zona zona, Usuario usuarioEditor,
			Usuario usuarioInsersor) {
		this.dono=veiculoForm.getDono();
		this.placa=veiculoForm.getPlaca();
		this.localDoAlerta=veiculoForm.getLocalDoAlerta();
		this.momentoDoAlerta=veiculoForm.getMomentoDoAlerta();
		this.alertado=veiculoForm.getAlertado();
		this.nivelDeUrgencia=veiculoForm.getNivelDeUrgencia();
		this.statusDoVeiculo=veiculoForm.getStatusDoVeiculo();
		this.createdAt=LocalDateTime.now();
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
				+ nivelDeUrgencia + ", statusDoVeiculo=" + statusDoVeiculo + ", zona=" + "zona" + ", usuarioInsersor="
				+ "insersor" + ", ultimoUsuarioEditor=" + "editor" + ", próprio_da_filha"+"]";
	}
	
	
}
