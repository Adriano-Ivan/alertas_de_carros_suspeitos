package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.form_util.MontadorEValidadorDeUsuarioEzona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.BotDoTelegram;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.BotDoTelegramDeUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.BotDoTelegramDeUsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.BotDoTelegramRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class BotDoTelegramDeUsuarioForm extends MontadorEValidadorDeUsuarioEzona{

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
	
	public BotDoTelegram montarBotDoTelegram(BotDoTelegramRepository botDoTelegramRepository) {
		Optional<BotDoTelegram> botDoTelegramOpt=botDoTelegramRepository.findById(idBotDoTelegram);
		BotDoTelegram botDoTelegram=botDoTelegramOpt.orElse(null);
		
		return botDoTelegram;
	}

	private boolean validarBotDoTelegram(BotDoTelegramRepository botDoTelegramRepository) {
		if(idBotDoTelegram == null) return false;
		
		BotDoTelegram botDoTelegram = montarBotDoTelegram(botDoTelegramRepository);
		
		if(botDoTelegram == null) {
			System.out.println("ENTROU");
			return false;
		}
		
		return true;
	}
	
	private Usuario montarUsuarioAlertado(UsuarioRepository usuarioRepository) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
		Usuario usuario = usuarioOpt.orElse(null);
		
		return usuario;
	}
	
	private boolean validarUsuarioAlertado(UsuarioRepository usuarioRepository) {
		if(idUsuario==null) return false;
		
		Usuario usuario = montarUsuarioAlertado(usuarioRepository);
		
		if(usuario == null) {
			return false;
		}
		
		return true;
	}
	public boolean validarBotDoTelegramEusuarioEzona(BotDoTelegramRepository botDoTelegramRepository,
			UsuarioRepository usuarioRepository,ZonaRepository zonaRepository) {
		return validarBotDoTelegram(botDoTelegramRepository) && validarUsuarioAlertado(usuarioRepository) &&
				validarZona(zonaRepository);
	}
	
	public BotDoTelegramDeUsuario converter(BotDoTelegramRepository botDoTelegramRepository,
			UsuarioRepository usuarioRepository) {
		return new BotDoTelegramDeUsuario(montarUsuarioAlertado(usuarioRepository), montarBotDoTelegram(botDoTelegramRepository),
				montarUsuarioInsersor(usuarioRepository));
	}
	
	public BotDoTelegramDeUsuario atualizar(Long id,BotDoTelegramDeUsuarioRepository botDoTelegramDeUsuarioRepository,
			BotDoTelegramRepository botDoTelegramRepository,
			UsuarioRepository usuarioRepository,ZonaRepository zonaRepository) {
		BotDoTelegramDeUsuario botDoTelegramDeUsuario = botDoTelegramDeUsuarioRepository.getById(id);
		
		BotDoTelegram botDoTelegram = montarBotDoTelegram(botDoTelegramRepository);
		Usuario usuario = montarUsuarioAlertado(usuarioRepository);
		
		botDoTelegramDeUsuario.setZona(montarZona(idZona, zonaRepository));
		botDoTelegramDeUsuario.setUltimoUsuarioEditor(montarUltimoUsuarioEditor(usuarioRepository));
		botDoTelegramDeUsuario.setBotDoTelegram(botDoTelegram);
		botDoTelegramDeUsuario.setUsuario(usuario);
		botDoTelegramDeUsuario.setNomeDoBot(botDoTelegram.getDenominacao());
		botDoTelegramDeUsuario.setNomeDoUsuario(usuario.getNomeDeUsuario());
		botDoTelegramDeUsuario.setUpdatedAt(LocalDateTime.now());
		
		return botDoTelegramDeUsuario;
	}
}
