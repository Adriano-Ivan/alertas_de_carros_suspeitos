package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem.MensagemEnviadaForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem.MensagemRecebidaForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.mensagem.MensagemEnviadaDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="mensagens_enviadas")
public class MensagemEnviada extends Mensagem{
	public MensagemEnviada() {}

	public MensagemEnviada(MensagemEnviadaForm mensagemEnviadaForm, Usuario usuario) {
		super(mensagemEnviadaForm,usuario);
	}

	public static Page<MensagemEnviadaDto> converter(Page<MensagemEnviada> mensagensEnviadas) {
		return mensagensEnviadas.map(MensagemEnviadaDto::new);
	}

	public static MensagemEnviadaDto converter(MensagemEnviada mensagemEnviada) {
		return new MensagemEnviadaDto(mensagemEnviada);
	}
}
