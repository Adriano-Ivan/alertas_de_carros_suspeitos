package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@MappedSuperclass
public class Alerta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	protected Usuario usuario;
	
	@Column(length=7)
	protected String placa;
	
	@Column(length=130)
	protected String nomeDeUsuario;
	
	protected Boolean alerta_emitido;
	
	public Alerta() {}

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

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public Boolean getAlerta_emitido() {
		return alerta_emitido;
	}

	public void setAlerta_emitido(Boolean alerta_emitido) {
		this.alerta_emitido = alerta_emitido;
	}

	@Override
	public String toString() {
		return "Alerta [id=" + id +", ocorrência="+"classe_filha"+ ", usuario=" + usuario + ", placa=" + placa + ", nomeDeUsuario=" + nomeDeUsuario
				+ "]";
	}
	
	
}
