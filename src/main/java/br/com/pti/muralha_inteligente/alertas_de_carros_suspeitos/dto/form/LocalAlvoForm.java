package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.LocalAlvo;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.LocalAlvoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class LocalAlvoForm {

	@NotNull @NotBlank @Size(min=2)
	private String local;
	
	private Long idZonaAssociada;
	
	private Long idUsuarioInsersor;
	
	private Long idUltimoUsuarioEditor;

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Long getIdZonaAssociada() {
		return idZonaAssociada;
	}

	public void setIdZonaAssociada(Long idZonaAssociada) {
		this.idZonaAssociada = idZonaAssociada;
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
	
	private Usuario definirUsuario(Long id, UsuarioRepository usuarioRepository ) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		Usuario usuario=usuarioOpt.orElse(null);
		return usuario;
	}
	
	private Zona definirZona(Long id, ZonaRepository zonaRepository) {
		Optional<Zona> zonaOpt = zonaRepository.findById(id);
		Zona zonaAssociada = zonaOpt.orElse(null);
		return zonaAssociada;
	}
	public LocalAlvo converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {	
		return new LocalAlvo(local,definirUsuario(idUsuarioInsersor,
				usuarioRepository),definirZona(idZonaAssociada,zonaRepository));
	}
	
	public LocalAlvo atualizar(Long id, LocalAlvoRepository localAlvoRepository,
			ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		LocalAlvo localAlvo=localAlvoRepository.getById(id);
		
		localAlvo.setLocal(local);
		localAlvo.setUltimoUsuarioEditor(definirUsuario(idUltimoUsuarioEditor, usuarioRepository));
		localAlvo.setZonaAssociada(definirZona(idZonaAssociada, zonaRepository));
		localAlvo.setUpdatedAt(LocalDateTime.now());
		
		return localAlvo;
	}
}
