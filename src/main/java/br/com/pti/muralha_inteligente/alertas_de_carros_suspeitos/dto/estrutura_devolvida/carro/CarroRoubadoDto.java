package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;

public class CarroRoubadoDto extends CarroDto {
	private String localDoRoubo;
	
	public CarroRoubadoDto(CarroRoubado veiculo) {
		super(veiculo);
		this.localDoRoubo=veiculo.getLocalDoRoubo();

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
	
	public String getLocalDoRoubo() {
		return this.localDoRoubo;
	}
}