package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tarefa_para_fazer")
public class TarefaParaFazer extends Pendencia{
	private Boolean cumprida;
	
	public TarefaParaFazer() {}

	public Boolean getCumprida() {
		return cumprida;
	}

	public void setCumprida(Boolean cumprida) {
		this.cumprida = cumprida;
	}
	
	
}
