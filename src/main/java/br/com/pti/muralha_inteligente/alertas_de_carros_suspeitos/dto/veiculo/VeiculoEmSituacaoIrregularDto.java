package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator.MedidaAdministrativa;

public class VeiculoEmSituacaoIrregularDto extends VeiculoDto{

	private MedidaAdministrativa medidaAdministrativa;
	
	public VeiculoEmSituacaoIrregularDto(VeiculoEmSituacaoIrregular veiculo) {
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
