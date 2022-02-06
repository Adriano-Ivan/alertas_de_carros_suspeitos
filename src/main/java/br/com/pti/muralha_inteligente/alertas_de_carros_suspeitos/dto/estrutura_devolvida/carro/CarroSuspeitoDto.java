package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;

public class CarroSuspeitoDto extends CarroDto{

	private String justificativa;

	public CarroSuspeitoDto(CarroSuspeito veiculo) {
		super(veiculo);
		this.justificativa = veiculo.getJustificativa();
	}

	public String getJustificativa() {
		return justificativa;
	}

}
