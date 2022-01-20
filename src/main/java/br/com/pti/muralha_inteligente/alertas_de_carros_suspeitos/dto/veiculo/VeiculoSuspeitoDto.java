package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;

public class VeiculoSuspeitoDto extends VeiculoDto{

	private String justificativa;

	public VeiculoSuspeitoDto(VeiculoSuspeito veiculo) {
		super(veiculo);
		this.justificativa = veiculo.getJustificativa();

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


	public String getJustificativa() {
		return justificativa;
	}

}
