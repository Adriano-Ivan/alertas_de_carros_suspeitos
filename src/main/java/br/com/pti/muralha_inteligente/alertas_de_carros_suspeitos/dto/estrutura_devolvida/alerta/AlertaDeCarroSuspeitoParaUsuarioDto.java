package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroSuspeitoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;

public class AlertaDeCarroSuspeitoParaUsuarioDto extends AlertaDto {

	private CarroSuspeitoDto carroSuspeitoDto;
	
	public AlertaDeCarroSuspeitoParaUsuarioDto(AlertaDeCarroSuspeitoParaUsuario alerta) {
		super(alerta);
		this.carroSuspeitoDto=new CarroSuspeitoDto( alerta.getCarroSuspeito());
	}

	public CarroSuspeitoDto getCarroSuspeito() {
		return carroSuspeitoDto;
	}
}
