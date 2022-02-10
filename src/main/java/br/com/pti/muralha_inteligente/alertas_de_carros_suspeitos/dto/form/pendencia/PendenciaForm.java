package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.pendencia;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.form_util.MontadorEValidadorDeUsuarioEzona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.ObservacaoPertinente;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.Pendencia;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;

public abstract class PendenciaForm extends MontadorEValidadorDeUsuarioEzona {
	
	@NotNull @NotBlank @Size(min=4)
	private String descricao;

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void atualizar(Pendencia pendencia,UsuarioRepository usuarioRepository) {
		pendencia.setDescricao(descricao);
		pendencia.setUpdatedAt(LocalDateTime.now());
		pendencia.setUltimoUsuarioEditor(this.montarUltimoUsuarioEditor(usuarioRepository));
	}
	

}
