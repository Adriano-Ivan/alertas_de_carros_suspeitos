package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.Carro;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;

public abstract class AlertaParaUsuarioForm {

	@NotNull @NotBlank
	private String latitude;
	
	@NotNull @NotBlank
	private String longitude;
	
	@NotNull
	private Boolean alertaEmitido;
	
	@NotNull
	private Long idUsuario;
	
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
	
	public Boolean getAlertaEmitido() {
		return alertaEmitido;
	}
	
	public void setAlertaEmitido(Boolean alertaEmitido) {
		this.alertaEmitido = alertaEmitido;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	protected Usuario encontrarUsuario(UsuarioRepository usuarioRepository) {
		Optional<Usuario> usuarioOpt=usuarioRepository.findById(idUsuario);
		Usuario usuario = usuarioOpt.orElse(null);
		
		return null;
	}
	public boolean validarUsuario(UsuarioRepository usuarioRepository) {
		Usuario usuario = encontrarUsuario(usuarioRepository);
		
		if(usuario == null) { 
			return false;
		}
		return true;
	}
	
	protected void atualizar(AlertaParaUsuario alertaParaUsuario,
			UsuarioRepository usuarioRepository, Carro carro) {
		Usuario usuario = usuarioRepository.getById(idUsuario);
		
		alertaParaUsuario.setAlertaEmitido(alertaEmitido);
		alertaParaUsuario.setLongitude(longitude);
		alertaParaUsuario.setLatitude(latitude);
		alertaParaUsuario.setPlaca(carro.getPlaca());
		alertaParaUsuario.setUpdatedAt(LocalDateTime.now());
		
		alertaParaUsuario.setUsuario(usuario);
		alertaParaUsuario.setNomeDeUsuario(usuario.getNomeDeUsuario());
	}
}
