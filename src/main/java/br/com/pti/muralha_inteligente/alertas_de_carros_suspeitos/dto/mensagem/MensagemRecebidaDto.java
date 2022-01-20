package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.mensagem;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.Mensagem;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemRecebida;

public class MensagemRecebidaDto extends  MensagemDto{

	public MensagemRecebidaDto(Mensagem mensagem) {
		super(mensagem);
	}
}
