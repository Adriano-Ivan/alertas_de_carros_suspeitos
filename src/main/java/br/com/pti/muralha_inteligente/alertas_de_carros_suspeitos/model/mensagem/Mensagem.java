package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
