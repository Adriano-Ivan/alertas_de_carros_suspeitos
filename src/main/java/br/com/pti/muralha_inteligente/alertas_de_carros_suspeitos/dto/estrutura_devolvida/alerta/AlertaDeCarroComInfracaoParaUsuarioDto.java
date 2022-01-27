package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroComInfracaoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroComInfracao;

public class AlertaDeCarroComInfracaoParaUsuarioDto extends AlertaDto {

	private CarroComInfracao carroComInfracao;
	
	public AlertaDeCarroComInfracaoParaUsuarioDto(AlertaDeCarroComInfracaoParaUsuario alerta) {
		super(alerta);
		this.carroComInfracao=alerta.getCarroComInfracao();
	}

	public CarroComInfracao getCarroComInfracao() {
		return carroComInfracao;
	}
}
