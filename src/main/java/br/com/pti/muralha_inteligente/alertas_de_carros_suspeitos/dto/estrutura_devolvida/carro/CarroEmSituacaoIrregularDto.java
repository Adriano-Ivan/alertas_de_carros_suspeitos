package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.enumerator.MedidaAdministrativa;

public class CarroEmSituacaoIrregularDto extends CarroDto{

	private MedidaAdministrativa medidaAdministrativa;
	
	public CarroEmSituacaoIrregularDto(CarroEmSituacaoIrregular veiculo) {
		super(veiculo);
		this.medidaAdministrativa=veiculo.getMedidaAdministrativa();

		if (veiculo.getZona() != null) {
			this.idZona = veiculo.getZona().getId();
		}
		if (veiculo.getUsuarioInsersor() != null) {
			this.idUsuarioInsersor = veiculo.getUsuarioInsersor().getId();
		}
		if (veiculo.getUltimoUsuarioEditor() != null) {
			this.idUltimoUsuarioEditor = veiculo.getUltimoUsuarioEditor().getId();
		}
		
	}
	
	public MedidaAdministrativa getMedidaAdministrativa() {
		return medidaAdministrativa;
	}
}
