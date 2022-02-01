package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.Mensagem;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;

public abstract class MensagemForm {

	@NotNull @NotBlank
	private  String mensagem;
	
	@NotNull
	private Long idUsuario;
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime createdAt;

	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	private Usuario montarUsuario(UsuarioRepository usuarioRepository) {
		Optional<Usuario> usuarioOpt=usuarioRepository.findById(idUsuario);
		Usuario usuario = usuarioOpt.orElse(null);
		
		return usuario;
	}
	
	public boolean validarUsuario(UsuarioRepository usuarioRepository) {
		Usuario usuario = montarUsuario(usuarioRepository);
		
		if(usuario == null) { 
			return false;
		}
		return true;
	}
	
	protected void atualizar(Mensagem msg,Long id,UsuarioRepository usuarioRepository) {
		Usuario usuario = montarUsuario(usuarioRepository);
		
		msg.setMensagem(mensagem);
		msg.setUpdatedAt(LocalDateTime.now());
		msg.setUsuario(usuario);
	}

	protected Usuario delegarUsuarioEdataDeCriacao(UsuarioRepository usuarioRepository) {
		Usuario usuario = montarUsuario(usuarioRepository);
		this.createdAt=LocalDateTime.now();
		return usuario;
	}
}
