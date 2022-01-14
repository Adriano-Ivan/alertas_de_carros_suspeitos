package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="niveis_de_urgencia")
public class NivelDeUrgencia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private NivelUrgencia nivelUrgencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NivelUrgencia getNivelUrgencia() {
		return nivelUrgencia;
	}

	public void setNivelUrgencia(NivelUrgencia nivelUrgencia) {
		this.nivelUrgencia = nivelUrgencia;
	}
	
	
}
