package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.LocalAlvoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="locais_alvo")
public class LocalAlvo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=120,nullable=false)
	private String local;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="local-user-movement")
	private Usuario usuarioInsersor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="local-user-edited-movement")
	private Usuario ultimoUsuarioEditor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="local-zone-movement")
	private Zona zonaAssociada;
	
	protected LocalDateTime createdAt;
	
	protected LocalDateTime updatedAt;
	
	public LocalAlvo() {}
	
	public LocalAlvo(String local, Usuario usuarioInsersor, Zona zonaAssociada) {
		this.local=local;
		this.usuarioInsersor=usuarioInsersor;
		this.zonaAssociada=zonaAssociada;
		this.createdAt=LocalDateTime.now();
	}

	public Zona getZonaAssociada() {
		return zonaAssociada;
	}

	public void setZonaAssociada(Zona zonaAssociada) {
		this.zonaAssociada = zonaAssociada;
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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@JsonBackReference(value="local-zone-movement")
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
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

	@Override
	public String toString() {
		return "LocalAlvo [id=" + id + ", local=" + local + ", usuarioInsersor=" + usuarioInsersor
				+ ", ultimoUsuarioEditor=" + ultimoUsuarioEditor + ", zonaAssociada=" + zonaAssociada + "]";
	}

	public static Page<LocalAlvoDto> converter(Page<LocalAlvo> locaisAlvo) {
		return locaisAlvo.map(LocalAlvoDto::new);
	}

	public static LocalAlvoDto converter(LocalAlvo localAlvo) {
		return new LocalAlvoDto(localAlvo);
	}
	
	
}
