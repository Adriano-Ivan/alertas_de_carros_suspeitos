package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.mensagem.MensagemRecebidaDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem.MensagemRecebidaForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="mensagens_recebidas")
public class MensagemRecebida extends Mensagem {

	public MensagemRecebida() {}

	public MensagemRecebida(MensagemRecebidaForm mensagemRecebidaForm, Usuario usuario) {
		super(mensagemRecebidaForm,usuario);
	}

	public static Page<MensagemRecebidaDto> converter(Page<MensagemRecebida> mensagensRecebidas) {
		return mensagensRecebidas.map(MensagemRecebidaDto::new);
	}

	public static MensagemRecebidaDto converter(MensagemRecebida mensagemRecebida) {
		return new MensagemRecebidaDto(mensagemRecebida);
	}
}
