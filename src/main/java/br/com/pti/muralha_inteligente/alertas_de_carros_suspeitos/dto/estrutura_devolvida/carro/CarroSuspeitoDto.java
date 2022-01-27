package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;

public class CarroSuspeitoDto extends CarroDto{

	private String justificativa;

	public CarroSuspeitoDto(CarroSuspeito veiculo) {
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
