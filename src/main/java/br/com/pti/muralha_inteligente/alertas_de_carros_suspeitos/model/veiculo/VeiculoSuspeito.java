package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="veiculos_suspeitos")
public class VeiculoSuspeito extends Veiculo {
	
	@Column(columnDefinition = "TEXT")
	private String justificativa;
	
	public VeiculoSuspeito() {}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
}
