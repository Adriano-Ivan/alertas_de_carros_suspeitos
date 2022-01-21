package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.pendencia;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.ObservacaoPertinente;

public class ObservacaoPertinenteDto extends PendenciaDto {

	private Boolean contemplada;

	public ObservacaoPertinenteDto(ObservacaoPertinente observacaoPertinente) {
		super(observacaoPertinente);
		this.contemplada=observacaoPertinente.getContemplada();
	}
	public Boolean getContemplada() {
		return contemplada;
	}
	
	
}
