package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.pendencia;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.TarefaParaFazer;

public class TarefaParaFazerDto extends PendenciaDto {

	private Boolean cumprida;
	
	public TarefaParaFazerDto(TarefaParaFazer tarefaParaFazer) {
		super(tarefaParaFazer);
		this.cumprida=tarefaParaFazer.getCumprida();
	}

	public Boolean getCumprida() {
		return cumprida;
	}
}
