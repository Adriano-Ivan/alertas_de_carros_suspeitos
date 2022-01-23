package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class ZonaForm {

	@NotNull @NotBlank @Size(min=2)
	private String zona;
	
	private Long idUsuarioInsersor;
	private Long idUltimoUsuarioEditor;

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
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

	public Zona converter(UsuarioRepository usuarioRepository) {
		if(idUsuarioInsersor==null) {
			idUsuarioInsersor=(long) 0;
		}
		Optional<Usuario> usuarioOpt=usuarioRepository.findById(idUsuarioInsersor);
		Usuario usuario = usuarioOpt.orElse(null);
		
		if(usuario!=null) {
			return new Zona(this,usuario);
		}else {
			return new Zona(this,null);
		}
	}

	public Zona atualizar(Long id, ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		Zona zonaObj = zonaRepository.getById(id);
		
		Optional<Usuario> usuarioOpt=usuarioRepository.findById(idUsuarioInsersor);
		Usuario usuario = usuarioOpt.orElse(null);

		zonaObj.setUltimoUsuarioEditor(usuario);

		zonaObj.setZona(zona);
		zonaObj.setUpdatedAt(LocalDateTime.now());
		return zonaObj;
	}
}
