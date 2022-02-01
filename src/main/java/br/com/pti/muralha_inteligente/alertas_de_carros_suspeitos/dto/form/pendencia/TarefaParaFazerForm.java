package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.pendencia;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.TarefaParaFazer;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.pendencia.TarefaParaFazerRepository;

public class TarefaParaFazerForm extends PendenciaForm{

	@NotNull
	private Boolean cumprida;

	public Boolean getCumprida() {
		return cumprida;
	}

	public void setCumprida(Boolean cumprida) {
		this.cumprida = cumprida;
	}
	
	public TarefaParaFazer converter(UsuarioRepository usuarioRepository) {
		return new TarefaParaFazer(this,this.montarUsuarioInsersor(usuarioRepository));
	}
	
	public TarefaParaFazer atualizar(Long id, TarefaParaFazerRepository tarefaParaFazerRepository,
			UsuarioRepository usuarioRepository) {
		TarefaParaFazer tarefaParaFazer = tarefaParaFazerRepository.getById(id);
		super.atualizar(tarefaParaFazer,usuarioRepository);
		tarefaParaFazer.setCumprida(cumprida);
		return tarefaParaFazer;
	}
}
