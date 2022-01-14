package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="veiculos_roubados")
public class VeiculoRoubado extends Veiculo{
	
	@Column(length=80)
	private String localDoRoubo;
	
	public VeiculoRoubado() {}

	public String getLocalDoRoubo() {
		return localDoRoubo;
	}

	public void setLocalDoRoubo(String localDoRoubo) {
		this.localDoRoubo = localDoRoubo;
	}
	
	
}
