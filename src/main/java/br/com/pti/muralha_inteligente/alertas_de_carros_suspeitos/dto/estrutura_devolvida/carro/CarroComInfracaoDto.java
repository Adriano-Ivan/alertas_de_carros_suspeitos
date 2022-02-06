package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.enumerator.GravidadeDaInfracao;

public class CarroComInfracaoDto extends CarroDto{

	private GravidadeDaInfracao gravidadeDaInfracao;
	
	public CarroComInfracaoDto(CarroComInfracao veiculo) {
		super(veiculo);
		this.gravidadeDaInfracao=veiculo.getGravidadeDaInfracao();
	}
	
	public GravidadeDaInfracao getGravidadeDaInfracao() {
		return gravidadeDaInfracao;
	}
}
