package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.form_util.MontadorEValidadorDeUsuarioEzona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.Carro;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public abstract class CarroForm extends MontadorEValidadorDeUsuarioEzona {

	@NotBlank @NotNull @Size(min=2)
	private String dono;
	
	@NotBlank @NotNull @Size(min=7, max=7, message="campo deve ter 7 caracteres")
	private String placa;
	
	@NotBlank @NotNull @Size(min=2)
	private String localDoAlerta;
	
	@NotNull
	private LocalDateTime momentoDoAlerta;
	
	@NotNull 
	private Boolean alertado;
	
	@NotNull @NotBlank @Size(min=2)
	private String nivelDeUrgencia;
	
	@NotNull @NotBlank @Size(min=2)
	private String statusDoVeiculo;

	private String latitude;
	
	private String longitude;
	
	public CarroForm() {}
	
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

	protected Zona montarZona(ZonaRepository zonaRepository) {
		return super.montarZona(idUltimoUsuarioEditor, zonaRepository);
	}
	protected void atualizar(Carro carro) {
		carro.setAlertado(alertado);
		carro.setLocalDoAlerta(localDoAlerta);
		carro.setDono(dono);
		carro.setMomentoDoAlerta(momentoDoAlerta);
		carro.setStatusDoVeiculo(statusDoVeiculo);
		carro.setPlaca(placa);
		carro.setLatitude(latitude);
		carro.setLongitude(longitude);
		carro.setNivelDeUrgencia(nivelDeUrgencia);
		carro.setUpdatedAt(LocalDateTime.now());
	}
	
	public boolean validarUsuarioInsersorEzona(UsuarioRepository usuarioRepository,ZonaRepository zonaRepository) {
		return validarUsuarioInsersor(usuarioRepository) && validarZona(zonaRepository);
	}
	
	public boolean validarUsuarioEditorEzona(UsuarioRepository usuarioRepository,ZonaRepository zonaRepository) {
		return validarUltimoUsuarioEditor(usuarioRepository) && validarZona(zonaRepository);
	}
	
}
