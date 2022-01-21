package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class ZonaForm {

	@NotNull @NotBlank @Size(min=2)
	private String zona;

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Zona converter() {
		return new Zona(zona);
	}

	public Zona atualizar(Long id, ZonaRepository zonaRepository) {
		Zona zonaObj = zonaRepository.getById(id);
		zonaObj.setZona(zona);
		zonaObj.setUpdatedAt(LocalDateTime.now());
		return zonaObj;
	}
}
