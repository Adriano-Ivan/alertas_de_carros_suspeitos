package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;

public class CarroRoubadoDto extends CarroDto {
	private String localDoRoubo;
	
	public CarroRoubadoDto(CarroRoubado veiculo) {
		super(veiculo);
		this.localDoRoubo=veiculo.getLocalDoRoubo();
	}
	
	public String getLocalDoRoubo() {
		return this.localDoRoubo;
	}
}
