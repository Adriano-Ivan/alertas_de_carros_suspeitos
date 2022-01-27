package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem;

import java.time.LocalDateTime;
import java.util.Optional;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemEnviada;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemRecebida;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.mensagem.MensagemEnviadaRepository;

public class MensagemEnviadaForm extends MensagemForm{

	public MensagemEnviada converter(UsuarioRepository usuarioRepository) {
		return new MensagemEnviada(this,super.delegarUsuarioEdataDeCriacao(usuarioRepository));
	}

	public MensagemEnviada atualizar(Long id,MensagemEnviadaRepository mensagemEnviadaRepository,
			UsuarioRepository usuarioRepository
			) {
		MensagemEnviada mensagemEnviada =mensagemEnviadaRepository.getById(id);
		super.atualizar(mensagemEnviada,id,usuarioRepository);
		return mensagemEnviada;
	}
}
