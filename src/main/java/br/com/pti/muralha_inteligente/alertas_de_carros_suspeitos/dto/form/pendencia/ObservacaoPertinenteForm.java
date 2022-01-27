package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.pendencia;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.ObservacaoPertinente;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.pendencia.ObservacaoPertinenteRepository;

public class ObservacaoPertinenteForm extends PendenciaForm {

	@NotNull @NotNull
	private Boolean contemplada;

	public Boolean getContemplada() {
		return contemplada;
	}

	public void setContemplada(Boolean contemplada) {
		this.contemplada = contemplada;
	}
	
	public ObservacaoPertinente converter() {
		return new ObservacaoPertinente(descricao,contemplada);
	}
	public ObservacaoPertinente atualizar(Long id, ObservacaoPertinenteRepository observacaoPertinenteRepository) {
		ObservacaoPertinente observacaoPertinenteObjeto= observacaoPertinenteRepository.getById(id);
		super.atualizar(observacaoPertinenteObjeto);
		observacaoPertinenteObjeto.setContemplada(contemplada);
		return observacaoPertinenteObjeto;
	}
	
}
