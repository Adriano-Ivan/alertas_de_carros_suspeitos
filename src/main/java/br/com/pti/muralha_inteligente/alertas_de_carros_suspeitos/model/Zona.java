package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.ZonaDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.ZonaForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="zonas")
public class Zona {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=40,nullable=false)
	private String zona;

	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuarioInsersor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario ultimoUsuarioEditor;
	
	public Zona() {}
	public Zona(ZonaForm zonaForm,Usuario usuario) {
		zona=zonaForm.getZona();
		usuarioInsersor=usuario;
		this.createdAt=LocalDateTime.now();
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

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
		
	}
	@Override
	public String toString() {
		return "Zona [id=" + id + ", zona=" + zona + ", usuarioInsersor=" + usuarioInsersor + ", ultimoUsuarioEditor="
				+ ultimoUsuarioEditor + "]";
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
	public static Page<ZonaDto> converter(Page<Zona> zonas) {
		return zonas.map(ZonaDto::new);
	}
	public static ZonaDto converter(Zona zona2) {
		return new ZonaDto(zona2);
	}
	
}
