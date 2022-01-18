package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoSuspeitoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class VeiculoSuspeitoForm {
	
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
	
	@NotNull
	private Long idZona;
	
	@NotNull
	private Long idUsuarioInsersor;
	
	@NotNull
	private Long idUltimoUsuarioEditor;
	
	@NotNull @NotBlank @Size(min=5)
	private String justificativa;
	
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
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	public VeiculoSuspeito converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		Optional<Zona> zonaOpt = zonaRepository.findById(idZona);
		Zona zona = zonaOpt.orElse(null);
		
		Optional<Usuario> usuarioEditorOpt = usuarioRepository.findById(idUltimoUsuarioEditor);
		Usuario usuarioEditor = usuarioEditorOpt.orElse(null);
		
		Optional<Usuario> usuarioInsersorOpt = usuarioRepository.findById(idUsuarioInsersor);
		Usuario usuarioInsersor = usuarioInsersorOpt.orElse(null);
		
		return new VeiculoSuspeito(dono,placa,localDoAlerta,momentoDoAlerta,
				alertado,nivelDeUrgencia,statusDoVeiculo,justificativa,zona,usuarioEditor,usuarioInsersor);
	}
	
	public VeiculoSuspeito atualizar(Long id, VeiculoSuspeitoRepository veiculoSuspeitoRepository,
			ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		VeiculoSuspeito veiculo = veiculoSuspeitoRepository.getById(id);
		
		Optional<Zona> zonaOpt = zonaRepository.findById(idZona);
		Zona zona = zonaOpt.orElse(null);
		
		Optional<Usuario> usuarioEditorOpt = usuarioRepository.findById(idUltimoUsuarioEditor);
		Usuario usuarioEditor = usuarioEditorOpt.orElse(null);
		
		Optional<Usuario> usuarioInsersorOpt = usuarioRepository.findById(idUsuarioInsersor);
		Usuario usuarioInsersor = usuarioInsersorOpt.orElse(null);
		
		veiculo.setZona(zona);
		veiculo.setUltimoUsuarioEditor(usuarioEditor);
		veiculo.setUsuarioInsersor(usuarioInsersor);
		
		veiculo.setAlertado(alertado);
		veiculo.setLocalDoAlerta(localDoAlerta);
		veiculo.setDono(dono);
		veiculo.setMomentoDoAlerta(momentoDoAlerta);
		veiculo.setStatusDoVeiculo(statusDoVeiculo);
		veiculo.setPlaca(placa);
		veiculo.setJustificativa(justificativa);
		veiculo.setNivelDeUrgencia(nivelDeUrgencia);
		
		return veiculo;
	}
	
}
