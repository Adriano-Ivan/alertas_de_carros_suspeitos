package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.pendencia.TarefaParaFazerDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.pendencia.TarefaParaFazerForm;

@Entity
@Table(name="tarefas_para_fazer")
public class TarefaParaFazer extends Pendencia{
	
	private Boolean cumprida;

	public TarefaParaFazer() {}
	
	public TarefaParaFazer(TarefaParaFazerForm form) {
		super(form);
		this.cumprida=form.getCumprida();
	}

	public Boolean getCumprida() {
		return cumprida;
	}

	public void setCumprida(Boolean cumprida) {
		this.cumprida = cumprida;
	}

	@Override
	public String toString() {
		return "TarefaParaFazer [cumprida=" + cumprida + ", id=" +this.getId() + ", descricao=" + this.getDescricao()+ "]";
	}

	public static Page<TarefaParaFazerDto> converter(Page<TarefaParaFazer> tarefasParaFazer) {
		return tarefasParaFazer.map(TarefaParaFazerDto::new);
	}
	
	
}
