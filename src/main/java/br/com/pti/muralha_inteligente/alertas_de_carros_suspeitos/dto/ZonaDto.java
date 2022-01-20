package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;

public class ZonaDto {

	private Long id;
	private String zona;
	
	public ZonaDto(Zona zonaModel) {
		id=zonaModel.getId();
		zona=zonaModel.getZona();
	}

	public Long getId() {
		return id;
	}

	public String getZona() {
		return zona;
	}
	
	
}
