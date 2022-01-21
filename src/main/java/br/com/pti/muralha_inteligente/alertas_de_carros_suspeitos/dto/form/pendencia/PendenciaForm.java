package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.pendencia;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.ObservacaoPertinente;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.Pendencia;

public abstract class PendenciaForm {
	
	@NotNull @NotBlank @Size(min=4)
	protected String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void atualizar(Pendencia pendencia) {
		pendencia.setDescricao(descricao);
		pendencia.setUpdatedAt(LocalDateTime.now());
		
	}
	
}
