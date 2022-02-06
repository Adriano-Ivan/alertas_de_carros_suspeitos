package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;

public class ZonaDto {

	private Long id;
	private String zona;
	private Long idUltimoUsuarioEditor;
	private Long idUsuarioInsersor;
	private LocalDateTime createdAt;	
	private LocalDateTime updatedAt;
	
	public ZonaDto(Zona zona) {
		id=zona.getId();
		this.zona=zona.getZona();
		
		if(zona.getUltimoUsuarioEditor()!=null) {
			idUltimoUsuarioEditor=zona.getUltimoUsuarioEditor().getId();
		}
		if(zona.getUsuarioInsersor()!=null) {
			idUsuarioInsersor=zona.getUsuarioInsersor().getId();
		}
		if(zona.getCreatedAt()!=null) {
			createdAt=zona.getCreatedAt();
		}
		if(zona.getUpdatedAt()!=null) {
			updatedAt=zona.getUpdatedAt();
		}
	}

	public Long getId() {
		return id;
	}

	public String getZona() {
		return zona;
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
	
}
