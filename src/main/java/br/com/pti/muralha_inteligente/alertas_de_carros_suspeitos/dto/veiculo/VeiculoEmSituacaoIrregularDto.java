package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator.MedidaAdministrativa;

public class VeiculoEmSituacaoIrregularDto extends VeiculoDto{

	private MedidaAdministrativa medidaAdministrativa;
	
	public VeiculoEmSituacaoIrregularDto(VeiculoEmSituacaoIrregular veiculo) {
		super(veiculo);
		this.medidaAdministrativa=veiculo.getMedidaAdministrativa();
		
	}
	
	public MedidaAdministrativa getMedidaAdministrativa() {
		return medidaAdministrativa;
	}
}
