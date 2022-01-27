package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.pendencia;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.Pendencia;

public class PendenciaDto {

	private Long id;
	private String descricao;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public PendenciaDto() {}
	
	public PendenciaDto(Pendencia pendencia) {
		this.id=pendencia.getId();
		this.descricao=pendencia.getDescricao();
		this.createdAt=pendencia.getCreatedAt();
		this.updatedAt=pendencia.getUpdatedAt();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

}
