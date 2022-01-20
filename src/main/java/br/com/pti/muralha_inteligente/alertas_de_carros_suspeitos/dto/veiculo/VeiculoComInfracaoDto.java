package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator.GravidadeDaInfracao;

public class VeiculoComInfracaoDto extends VeiculoDto{

	private GravidadeDaInfracao gravidadeDaInfracao;
	
	public VeiculoComInfracaoDto(VeiculoComInfracao veiculo) {
		super(veiculo);
		this.gravidadeDaInfracao=veiculo.getGravidadeDaInfracao();
		

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
	
	public GravidadeDaInfracao getGravidadeDaInfracao() {
		return gravidadeDaInfracao;
	}
}
