package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroRoubadoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;

public class AlertaDeCarroRoubadoParaUsuarioDto extends AlertaDto {

	private CarroRoubado carroRoubado;
	
	public AlertaDeCarroRoubadoParaUsuarioDto(AlertaDeCarroRoubadoParaUsuario alerta) {
		super(alerta);
		this.carroRoubado=alerta.getCarroRoubado();
	}

	public CarroRoubado getCarroRoubado() {
		return carroRoubado;
	}
}
