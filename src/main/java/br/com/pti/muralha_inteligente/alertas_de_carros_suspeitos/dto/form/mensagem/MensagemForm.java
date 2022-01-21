package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.Mensagem;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;

public class MensagemForm {

	@NotNull @NotBlank
	protected  String mensagem;
	
	@NotNull
	protected Long idUsuario;
	
	protected LocalDateTime updatedAt;
	
	protected LocalDateTime createdAt;
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getUsuario() {
		return idUsuario;
	}

	public void setUsuario(Long idUsuario) {
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
	
	protected void atualizar(Mensagem msg,Long id,UsuarioRepository usuarioRepository) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		Usuario usr = usuario.orElse(null);
		
		msg.setMensagem(mensagem);
		msg.setUpdatedAt(LocalDateTime.now());
		msg.setUsuario(usr);
	}

	protected Usuario delegarUsuarioEdataDeCriacao(UsuarioRepository usuarioRepository) {
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		Usuario usr = usuario.orElse(null);
		this.createdAt=LocalDateTime.now();
		return usr;
	}
}
