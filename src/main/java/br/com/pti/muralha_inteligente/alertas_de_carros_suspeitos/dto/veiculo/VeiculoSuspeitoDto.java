package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;

public class VeiculoSuspeitoDto extends VeiculoDto{

	private String justificativa;

	public VeiculoSuspeitoDto(VeiculoSuspeito veiculo) {
		super(veiculo);
		this.justificativa = veiculo.getJustificativa();
	}


	public String getJustificativa() {
		return justificativa;
	}

}
