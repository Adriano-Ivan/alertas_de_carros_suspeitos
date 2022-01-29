package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem.MensagemForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem.MensagemRecebidaForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@MappedSuperclass
public abstract class Mensagem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario usuario;
	
	@Column(columnDefinition="TEXT")
	private String mensagem;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	public Mensagem() {}
	public Mensagem(MensagemForm mensagemRecebidaForm, Usuario usuario2) {
		mensagem=mensagemRecebidaForm.getMensagem();
		createdAt=mensagemRecebidaForm.getCreatedAt();
		this.usuario=usuario2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "Mensagem [id=" + id + ", usuario=" + usuario + ", mensagem=" + mensagem + ", momento_da_mensagem="
				+ createdAt + ", última atualização: "+ updatedAt+ "]";
	}
	
}
