package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator.MedidaAdministrativa;

@Entity
@Table(name="veiculos_em_situacao_irregular")
public class VeiculoEmSituacaoIrregular extends Veiculo {
	
	@Enumerated(EnumType.STRING)
	private MedidaAdministrativa medidaAdministrativa;
	
	public VeiculoEmSituacaoIrregular() {}

	public MedidaAdministrativa getMedidaAdministrativa() {
		return medidaAdministrativa;
	}

	public void setMedidaAdministrativa(MedidaAdministrativa medidaAdministrativa) {
		this.medidaAdministrativa = medidaAdministrativa;
	}

	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha", "medida administrativa: "+medidaAdministrativa.getDescricao());
	}
	
}
