package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.BotDoTelegram;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.BotDoTelegramDeUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.BotDoTelegramDeUsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.BotDoTelegramRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;

public class BotDoTelegramDeUsuarioForm {

	@NotNull
	private Long idUsuario;
	
	@NotNull
	private Long idBotDoTelegram;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdBotDoTelegram() {
		return idBotDoTelegram;
	}

	public void setIdBotDoTelegram(Long idBotDoTelegram) {
		this.idBotDoTelegram = idBotDoTelegram;
	}
	
	public BotDoTelegram definirBotDoTelegram(BotDoTelegramRepository botDoTelegramRepository) {
		Optional<BotDoTelegram> botDoTelegramOpt=botDoTelegramRepository.findById(idBotDoTelegram);
		BotDoTelegram botDoTelegram=botDoTelegramOpt.orElse(null);
		
		return botDoTelegram;
	}
	
	public Usuario definirUsuario(UsuarioRepository usuarioRepository) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
		Usuario usuario = usuarioOpt.orElse(null);
		
		return usuario;
	}
	
	public boolean validarBotDoTelegram(BotDoTelegramRepository botDoTelegramRepository) {
		BotDoTelegram botDoTelegram = definirBotDoTelegram(botDoTelegramRepository);
		
		if(botDoTelegram == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean validarUsuario(UsuarioRepository usuarioRepository) {
		Usuario usuario = definirUsuario(usuarioRepository);
		
		if(usuario == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean validarBotDoTelegramEusuario(BotDoTelegramRepository botDoTelegramRepository,
			UsuarioRepository usuarioRepository) {
		return validarBotDoTelegram(botDoTelegramRepository) && validarUsuario(usuarioRepository);
	}
	
	public BotDoTelegramDeUsuario converter(BotDoTelegramRepository botDoTelegramRepository,
			UsuarioRepository usuarioRepository) {
		return new BotDoTelegramDeUsuario(definirUsuario(usuarioRepository), definirBotDoTelegram(botDoTelegramRepository));
	}
	
	public BotDoTelegramDeUsuario atualizar(Long id,BotDoTelegramDeUsuarioRepository botDoTelegramDeUsuarioRepository,
			BotDoTelegramRepository botDoTelegramRepository,
			UsuarioRepository usuarioRepository) {
		BotDoTelegramDeUsuario botDoTelegramDeUsuario = botDoTelegramDeUsuarioRepository.getById(id);
		
		BotDoTelegram botDoTelegram = definirBotDoTelegram(botDoTelegramRepository);
		Usuario usuario = definirUsuario(usuarioRepository);
		
		botDoTelegramDeUsuario.setBotDoTelegram(botDoTelegram);
		botDoTelegramDeUsuario.setUsuario(usuario);
		botDoTelegramDeUsuario.setNomeDoBot(botDoTelegram.getDenominacao());
		botDoTelegramDeUsuario.setNomeDoUsuario(usuario.getNomeDeUsuario());
		botDoTelegramDeUsuario.setUpdatedAt(LocalDateTime.now());
		
		return botDoTelegramDeUsuario;
	}
}
