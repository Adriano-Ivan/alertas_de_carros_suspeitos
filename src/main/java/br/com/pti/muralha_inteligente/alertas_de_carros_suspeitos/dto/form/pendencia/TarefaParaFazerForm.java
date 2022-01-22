package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.pendencia;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.TarefaParaFazer;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.TarefaParaFazerRepository;

public class TarefaParaFazerForm extends PendenciaForm{

	@NotNull
	private Boolean cumprida;

	public Boolean getCumprida() {
		return cumprida;
	}

	public void setCumprida(Boolean cumprida) {
		this.cumprida = cumprida;
	}
	
	public TarefaParaFazer converter() {
		return new TarefaParaFazer(descricao, cumprida);
	}
	
	public TarefaParaFazer atualizar(Long id, TarefaParaFazerRepository tarefaParaFazerRepository) {
		TarefaParaFazer tarefaParaFazer = tarefaParaFazerRepository.getById(id);
		super.atualizar(tarefaParaFazer);
		tarefaParaFazer.setCumprida(cumprida);
		return tarefaParaFazer;
	}
}
