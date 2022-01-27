package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroSuspeitoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;

public class AlertaDeCarroSuspeitoParaUsuarioDto extends AlertaDto {

	private CarroSuspeito carroSuspeito;
	
	public AlertaDeCarroSuspeitoParaUsuarioDto(AlertaDeCarroSuspeitoParaUsuario alerta) {
		super(alerta);
		this.carroSuspeito=alerta.getCarroSuspeito();
	}

	public CarroSuspeito getCarroSuspeito() {
		return carroSuspeito;
	}
}
