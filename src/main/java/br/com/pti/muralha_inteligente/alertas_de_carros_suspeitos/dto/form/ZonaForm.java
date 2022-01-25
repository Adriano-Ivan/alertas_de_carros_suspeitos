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

	private Usuario definirUsuario(Long id,UsuarioRepository usuarioRepository) {
		Optional<Usuario> usuarioOpt=usuarioRepository.findById(id);
		Usuario usuario = usuarioOpt.orElse(null);
		return usuario;
	}
	public Zona converter(UsuarioRepository usuarioRepository) {
		if(idUsuarioInsersor==null) {
			idUsuarioInsersor=(long) 0;
		}
		Usuario usuario = definirUsuario(idUsuarioInsersor,usuarioRepository);
		
		return new Zona(this,usuario);
	}

	public Zona atualizar(Long id, ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		Zona zonaObj = zonaRepository.getById(id);

		if(idUltimoUsuarioEditor==null) {
			idUltimoUsuarioEditor=(long) 0;
		}
		Usuario usuario = definirUsuario(idUltimoUsuarioEditor,usuarioRepository);

		zonaObj.setUltimoUsuarioEditor(usuario);

		zonaObj.setZona(zona);
		zonaObj.setUpdatedAt(LocalDateTime.now());
		return zonaObj;
	}
}
