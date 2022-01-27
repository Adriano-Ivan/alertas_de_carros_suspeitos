package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.enumerator.GravidadeDaInfracao;

public class CarroComInfracaoDto extends CarroDto{

	private GravidadeDaInfracao gravidadeDaInfracao;
	
	public CarroComInfracaoDto(CarroComInfracao veiculo) {
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
