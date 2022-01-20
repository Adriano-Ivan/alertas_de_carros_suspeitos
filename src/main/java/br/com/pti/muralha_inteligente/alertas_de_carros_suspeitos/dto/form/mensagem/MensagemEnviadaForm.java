package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem;

import java.time.LocalDateTime;
import java.util.Optional;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemEnviada;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.MensagemEnviadaRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;

public class MensagemEnviadaForm extends MensagemForm{

	public MensagemEnviada converter(UsuarioRepository usuarioRepository) {
		Optional<Usuario> usrOpt = usuarioRepository.findById(idUsuario);
		Usuario usuario = usrOpt.orElse(null);
		this.createdAt=LocalDateTime.now();
		return new MensagemEnviada(this,usuario);
	}

	public MensagemEnviada atualizar(Long id,MensagemEnviadaRepository mensagemEnviadaRepository,
			UsuarioRepository usuarioRepository
			) {
		MensagemEnviada mensagemEnviada =mensagemEnviadaRepository.getById(id);
		super.atualizar(mensagemEnviada,id,usuarioRepository);
		return mensagemEnviada;
	}
}
