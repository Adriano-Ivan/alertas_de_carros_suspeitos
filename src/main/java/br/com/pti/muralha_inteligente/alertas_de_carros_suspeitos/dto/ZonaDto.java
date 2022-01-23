package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;

public class ZonaDto {

	private Long id;
	private String zona;
	private Long idUltimoUsuarioEditor;
	private Long idUsuarioInsersor;
	protected LocalDateTime createdAt;	
	protected LocalDateTime updatedAt;
	
	public ZonaDto(Zona zonaModel) {
		id=zonaModel.getId();
		zona=zonaModel.getZona();
		
		if(zonaModel.getUltimoUsuarioEditor()!=null) {
			idUltimoUsuarioEditor=zonaModel.getUltimoUsuarioEditor().getId();
		}
		if(zonaModel.getUsuarioInsersor()!=null) {
			idUsuarioInsersor=zonaModel.getUsuarioInsersor().getId();
		}
		if(zonaModel.getCreatedAt()!=null) {
			createdAt=zonaModel.getCreatedAt();
		}
		if(zonaModel.getUpdatedAt()!=null) {
			updatedAt=zonaModel.getUpdatedAt();
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
