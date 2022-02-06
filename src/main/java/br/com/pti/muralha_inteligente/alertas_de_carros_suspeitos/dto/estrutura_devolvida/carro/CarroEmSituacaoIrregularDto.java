package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.enumerator.MedidaAdministrativa;

public class CarroEmSituacaoIrregularDto extends CarroDto{

	private MedidaAdministrativa medidaAdministrativa;
	
	public CarroEmSituacaoIrregularDto(CarroEmSituacaoIrregular veiculo) {
		super(veiculo);
		this.medidaAdministrativa=veiculo.getMedidaAdministrativa();
		
	}
	
	public MedidaAdministrativa getMedidaAdministrativa() {
		return medidaAdministrativa;
	}
}
