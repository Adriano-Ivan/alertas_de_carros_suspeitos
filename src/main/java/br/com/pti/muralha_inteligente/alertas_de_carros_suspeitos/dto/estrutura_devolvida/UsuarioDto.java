package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.enumerator.TipoAutoridade;

public class UsuarioDto {

	private Long id;
	private String nomeDeUsuario;
	private String email;
	private String token;
	private TipoAutoridade autoridade;
	private Long insercoes;
	private Long idUltimoUsuarioEditor;
	private Long idUsuarioInsersor;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Zona zona;
	
	public UsuarioDto(Usuario usuario) {
		id=usuario.getId();
		nomeDeUsuario=usuario.getNomeDeUsuario();
		
		if(usuario.getToken()!=null)  token=usuario.getToken();
		if(usuario.getAutoridade()!=null)	autoridade=usuario.getAutoridade();
		if(usuario.getInsercoes()!=null)  insercoes=usuario.getInsercoes();
		if(usuario.getEmail()!=null)	email=usuario.getEmail();
		
		if(usuario.getUltimoUsuarioEditor()!=null) {
			idUltimoUsuarioEditor=usuario.getUltimoUsuarioEditor().getId();
		}
		
		if(usuario.getUsuarioInsersor()!=null) {
			idUsuarioInsersor=usuario.getUsuarioInsersor().getId();
		}
		
		if(usuario.getCreatedAt()!=null) {
			createdAt=usuario.getCreatedAt();
		}
		
		if(usuario.getUpdatedAt()!=null) {
			updatedAt=usuario.getUpdatedAt();
		}
		
		if(usuario.getZona()!=null) {
			zona=usuario.getZona();
		}
	}
		
	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}
	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}
	public String getToken() {
		return token;
	}
	public TipoAutoridade getAutoridade() {
		return autoridade;
	}
	public Long getInsercoes() {
		return insercoes;
	}
	public Long getIdUltimoUsuarioEditor() {
		return idUltimoUsuarioEditor;
	}
	public Long getIdUsuarioInsersor() {
		return idUsuarioInsersor;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public Zona getZona() {
		return zona;
	}
}
