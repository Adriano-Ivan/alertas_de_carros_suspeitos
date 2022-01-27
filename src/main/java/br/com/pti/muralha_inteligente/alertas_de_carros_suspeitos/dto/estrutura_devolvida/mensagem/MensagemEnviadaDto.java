package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.mensagem;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.Mensagem;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemEnviada;

public class MensagemEnviadaDto extends MensagemDto {

	public MensagemEnviadaDto(Mensagem mensagem) {
		super(mensagem);
	}
}
