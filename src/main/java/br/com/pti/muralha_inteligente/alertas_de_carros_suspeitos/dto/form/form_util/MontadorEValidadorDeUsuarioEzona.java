package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.form_util;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public abstract class MontadorEValidadorDeUsuarioEzona {
 
	protected Long idUsuarioInsersor;
	
	protected Long idUltimoUsuarioEditor;
	
	protected Long idZona;
	
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
	
	private Usuario montarUsuario(UsuarioRepository usuarioRepository, Long id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		Usuario usuario = usuarioOpt.orElse(null);
		
		return usuario;
	}
	
	protected Usuario montarUsuarioInsersor(UsuarioRepository usuarioRepository) {
		return montarUsuario(usuarioRepository,idUsuarioInsersor);
	}
	
	protected Usuario montarUltimoUsuarioEditor(UsuarioRepository usuarioRepository) {
		return montarUsuario(usuarioRepository, idUltimoUsuarioEditor);
	}
	
	private boolean validarUsuario(UsuarioRepository usuarioRepository,Long id) {
		if(id==null) return false;
		
		Usuario usuario = montarUsuario(usuarioRepository, id);
		
		if(usuario == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean validarUsuarioInsersor(UsuarioRepository usuarioRepository) {
		return validarUsuario(usuarioRepository,idUsuarioInsersor);
	}
	
	public boolean validarUltimoUsuarioEditor(UsuarioRepository usuarioRepository) {
		return validarUsuario(usuarioRepository,idUltimoUsuarioEditor);
	}
	

	protected Zona montarZona(Long id, ZonaRepository zonaRepository) {
		Optional<Zona> zonaOpt = zonaRepository.findById(id);
		Zona zonaAssociada = zonaOpt.orElse(null);
		return zonaAssociada;
		
	}
	
	protected boolean validarZona(ZonaRepository zonaRepository) {
		Zona zona = montarZona(idZona, zonaRepository);
		
		if(zona==null) {
			return false;
		}
		
		return true;
	}
}
