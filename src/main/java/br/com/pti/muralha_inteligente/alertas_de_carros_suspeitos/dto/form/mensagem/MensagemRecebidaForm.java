package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem;

import java.time.LocalDateTime;
import java.util.Optional;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemRecebida;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.MensagemRecebidaRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;

public class MensagemRecebidaForm extends MensagemForm{

	public MensagemRecebida converter(UsuarioRepository usuarioRepository) {
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		Usuario usr = usuario.orElse(null);
		this.createdAt=LocalDateTime.now();
		return new MensagemRecebida(this,usr);
	}

	public MensagemRecebida atualizar(Long id, MensagemRecebidaRepository mensagemRecebidaRepository,
			UsuarioRepository usuarioRepository) {
		MensagemRecebida mensagemRecebida = mensagemRecebidaRepository.getById(id);
		super.atualizar(mensagemRecebida,id,usuarioRepository);
		return mensagemRecebida;
	}

}
