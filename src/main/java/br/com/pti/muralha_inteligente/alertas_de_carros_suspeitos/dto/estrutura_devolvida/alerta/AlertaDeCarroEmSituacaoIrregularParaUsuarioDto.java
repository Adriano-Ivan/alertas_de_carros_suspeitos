package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroEmSituacaoIrregularParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroEmSituacaoIrregular;

public class AlertaDeCarroEmSituacaoIrregularParaUsuarioDto extends AlertaDto{

	private CarroEmSituacaoIrregular carroEmSituacaoIrregular;
	
	public AlertaDeCarroEmSituacaoIrregularParaUsuarioDto(AlertaDeCarroEmSituacaoIrregularParaUsuario alerta) {
		super(alerta);
		this.carroEmSituacaoIrregular=alerta.getCarroEmSituacaoIrregular();
	}

	public CarroEmSituacaoIrregular getCarroEmSituacaoIrregular() {
		return carroEmSituacaoIrregular;
	}
	
}
