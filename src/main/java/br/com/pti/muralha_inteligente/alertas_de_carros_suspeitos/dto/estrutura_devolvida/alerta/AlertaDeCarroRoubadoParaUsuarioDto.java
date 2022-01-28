package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroRoubadoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroRoubadoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;

public class AlertaDeCarroRoubadoParaUsuarioDto extends AlertaDto {

	private CarroRoubadoDto carroRoubadoDto;
	
	public AlertaDeCarroRoubadoParaUsuarioDto(AlertaDeCarroRoubadoParaUsuario alerta) {
		super(alerta);
		this.carroRoubadoDto=new CarroRoubadoDto(alerta.getCarroRoubado());
	}

	public CarroRoubadoDto getCarroRoubado() {
		return carroRoubadoDto;
	}
}
