package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.pendencia.PendenciaForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@MappedSuperclass
public abstract class Pendencia {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="TEXT")
	private String descricao;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuarioInsersor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario ultimoUsuarioEditor;
	
	public Pendencia() {}
	
	public Pendencia(PendenciaForm pendenciaForm,Usuario usuarioInsersor) {
		this.descricao=pendenciaForm.getDescricao();
		this.createdAt=LocalDateTime.now();
		this.usuarioInsersor=usuarioInsersor;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Usuario getUsuarioInsersor() {
		return usuarioInsersor;
	}

	public void setUsuarioInsersor(Usuario usuarioInsersor) {
		this.usuarioInsersor = usuarioInsersor;
	}

	public Usuario getUltimoUsuarioEditor() {
		return ultimoUsuarioEditor;
	}

	public void setUltimoUsuarioEditor(Usuario ultimoUsuarioEditor) {
		this.ultimoUsuarioEditor = ultimoUsuarioEditor;
	}
	
}
