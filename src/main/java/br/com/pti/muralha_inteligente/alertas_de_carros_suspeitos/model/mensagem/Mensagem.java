package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@MappedSuperclass
public abstract class Mensagem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	@ManyToOne
	protected Usuario usuario;
	
	@Column(columnDefinition="TEXT")
	protected String mensagem;
	
	protected LocalDateTime momento_da_mensagem=LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public LocalDateTime getMomento_da_mensagem() {
		return momento_da_mensagem;
	}

	public void setMomento_da_mensagem(LocalDateTime momento_da_mensagem) {
		this.momento_da_mensagem = momento_da_mensagem;
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
				+ momento_da_mensagem + "]";
	}
	
}
