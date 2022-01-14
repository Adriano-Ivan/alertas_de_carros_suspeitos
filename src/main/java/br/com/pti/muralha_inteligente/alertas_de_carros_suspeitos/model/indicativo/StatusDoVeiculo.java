package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="status_do_veiculo")
public class StatusDoVeiculo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private StatusVeiculo statusVeiculo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusVeiculo getStatusVeiculo() {
		return statusVeiculo;
	}

	public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
		this.statusVeiculo = statusVeiculo;
	}
	
	
}
