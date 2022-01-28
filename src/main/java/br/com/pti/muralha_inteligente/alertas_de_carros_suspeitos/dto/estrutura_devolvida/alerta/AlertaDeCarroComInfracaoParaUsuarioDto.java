package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroComInfracaoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroComInfracaoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroComInfracao;

public class AlertaDeCarroComInfracaoParaUsuarioDto extends AlertaDto {

	private CarroComInfracaoDto carroComInfracaoDto;
	
	public AlertaDeCarroComInfracaoParaUsuarioDto(AlertaDeCarroComInfracaoParaUsuario alerta) {
		super(alerta);
		this.carroComInfracaoDto=new CarroComInfracaoDto(alerta.getCarroComInfracao());
	}

	public CarroComInfracaoDto getCarroComInfracao() {
		return carroComInfracaoDto;
	}
}
