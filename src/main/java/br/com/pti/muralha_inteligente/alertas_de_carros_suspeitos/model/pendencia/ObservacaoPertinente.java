package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="observacoes_pertinentes")
public class ObservacaoPertinente extends Pendencia {

	private Boolean contemplada;
	
	public ObservacaoPertinente() {}

	public Boolean getContemplada() {
		return contemplada;
	}

	public void setContemplada(Boolean contemplada) {
		this.contemplada = contemplada;
	}
	
	
}
