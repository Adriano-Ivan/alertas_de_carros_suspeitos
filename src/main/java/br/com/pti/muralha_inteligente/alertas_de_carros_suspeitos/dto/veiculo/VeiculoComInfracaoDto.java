package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator.GravidadeDaInfracao;

public class VeiculoComInfracaoDto extends VeiculoDto{

	private GravidadeDaInfracao gravidadeDaInfracao;
	
	public VeiculoComInfracaoDto(VeiculoComInfracao veiculo) {
		super(veiculo);
		this.gravidadeDaInfracao=veiculo.getGravidadeDaInfracao();
	}
	
	public GravidadeDaInfracao getGravidadeDaInfracao() {
		return gravidadeDaInfracao;
	}
}
