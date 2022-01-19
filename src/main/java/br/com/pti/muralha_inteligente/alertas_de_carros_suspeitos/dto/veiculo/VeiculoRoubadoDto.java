package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;

public class VeiculoRoubadoDto extends VeiculoDto {
	private String localDoRoubo;
	
	public VeiculoRoubadoDto(VeiculoRoubado veiculo) {
		super(veiculo);
		this.localDoRoubo=veiculo.getLocalDoRoubo();
	}
	
	public String getLocalDoRoubo() {
		return this.localDoRoubo;
	}
}
