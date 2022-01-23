package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.LocalAlvo;

public class LocalAlvoDto {

	private Long id;
	private String local;
	private Long idUsuarioInsersor;
	private Long idUltimoUsuarioEditor;
	private Long idZonaAssociada;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public LocalAlvoDto(LocalAlvo localAlvo) {
		id=localAlvo.getId();
		local=localAlvo.getLocal();
		
		if(localAlvo.getUltimoUsuarioEditor()!=null) {
			idUltimoUsuarioEditor=localAlvo.getUltimoUsuarioEditor().getId();
		}
		if(localAlvo.getUsuarioInsersor()!=null) {
			idUsuarioInsersor=localAlvo.getUsuarioInsersor().getId();
		}
		if(localAlvo.getZonaAssociada()!=null) {
			idZonaAssociada=localAlvo.getZonaAssociada().getId();
		}
		if(localAlvo.getCreatedAt()!=null) {
			createdAt=localAlvo.getCreatedAt();
		}
		if(localAlvo.getUpdatedAt()!=null) {
			updatedAt=localAlvo.getUpdatedAt();
		}
	}

	public Long getId() {
		return id;
	}

	public String getLocal() {
		return local;
	}

	public Long getIdUsuarioInsersor() {
		return idUsuarioInsersor;
	}

	public Long getIdUltimoUsuarioEditor() {
		return idUltimoUsuarioEditor;
	}

	public Long getIdZonaAssociada() {
		return idZonaAssociada;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
}
