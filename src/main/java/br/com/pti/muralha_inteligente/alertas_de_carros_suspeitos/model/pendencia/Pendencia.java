package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.pendencia.PendenciaForm;

@MappedSuperclass
public abstract class Pendencia {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="TEXT")
	private String descricao;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

	public Pendencia() {}
	
	public Pendencia(PendenciaForm pendenciaForm) {
		this.descricao=pendenciaForm.getDescricao();
		this.createdAt=LocalDateTime.now();
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
	
	
}
