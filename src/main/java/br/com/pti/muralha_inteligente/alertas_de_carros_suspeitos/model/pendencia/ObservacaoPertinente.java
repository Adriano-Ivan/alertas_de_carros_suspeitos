package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="observacoes_pertinentes")
public class ObservacaoPertinente extends Pendencia {

	private Boolean contemplada;
	
	public ObservacaoPertinente() {}

	public ObservacaoPertinente( String descricao,
			 Boolean contemplada) {
		this.descricao=descricao;
		this.contemplada=contemplada;
		this.createdAt=LocalDateTime.now();
	}

	public Boolean getContemplada() {
		return contemplada;
	}

	public void setContemplada(Boolean contemplada) {
		this.contemplada = contemplada;
	}

	@Override
	public String toString() {
		return "ObservacaoPertinente [contemplada=" + contemplada + ", id=" + id + ", descricao=" + descricao + "]";
	}
	
	
}
