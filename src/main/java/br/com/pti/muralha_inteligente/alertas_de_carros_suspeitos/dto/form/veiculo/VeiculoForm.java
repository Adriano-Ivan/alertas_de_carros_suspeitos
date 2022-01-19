package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.Veiculo;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;

public class VeiculoForm {

	@NotBlank @NotNull @Size(min=2)
	protected String dono;
	
	@NotBlank @NotNull @Size(min=7, max=7, message="campo deve ter 7 caracteres")
	protected String placa;
	
	@NotBlank @NotNull @Size(min=2)
	protected String localDoAlerta;
	
	@NotNull
	protected LocalDateTime momentoDoAlerta;
	
	@NotNull 
	protected Boolean alertado;
	
	@NotNull @NotBlank @Size(min=2)
	protected String nivelDeUrgencia;
	
	@NotNull @NotBlank @Size(min=2)
	protected String statusDoVeiculo;
	
	@NotNull
	protected Long idZona;
	
	@NotNull
	protected Long idUsuarioInsersor;
	
	@NotNull
	protected Long idUltimoUsuarioEditor;
	
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
	public Long getIdZona() {
		return idZona;
	}
	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}
	public Long getIdUsuarioInsersor() {
		return idUsuarioInsersor;
	}
	public void setIdUsuarioInsersor(Long idUsuarioInsersor) {
		this.idUsuarioInsersor = idUsuarioInsersor;
	}
	public Long getIdUltimoUsuarioEditor() {
		return idUltimoUsuarioEditor;
	}
	public void setIdUltimoUsuarioEditor(Long idUltimoUsuarioEditor) {
		this.idUltimoUsuarioEditor = idUltimoUsuarioEditor;
	}
	public void atualizar(Veiculo veiculo, Zona zona, Usuario usuarioEditor, Usuario usuarioInsersor) {
		
		veiculo.setZona(zona);
		veiculo.setUltimoUsuarioEditor(usuarioEditor);
		veiculo.setUsuarioInsersor(usuarioInsersor);
		
		veiculo.setAlertado(alertado);
		veiculo.setLocalDoAlerta(localDoAlerta);
		veiculo.setDono(dono);
		veiculo.setMomentoDoAlerta(momentoDoAlerta);
		veiculo.setStatusDoVeiculo(statusDoVeiculo);
		veiculo.setPlaca(placa);
		
		veiculo.setNivelDeUrgencia(nivelDeUrgencia);
		
	}
	
	
}
