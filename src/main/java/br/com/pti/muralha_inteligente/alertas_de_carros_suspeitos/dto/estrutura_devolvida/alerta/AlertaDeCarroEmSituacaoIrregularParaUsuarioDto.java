package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroEmSituacaoIrregularDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroEmSituacaoIrregularParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroEmSituacaoIrregular;

public class AlertaDeCarroEmSituacaoIrregularParaUsuarioDto extends AlertaDto{

	private CarroEmSituacaoIrregularDto carroEmSituacaoIrregularDto;
	
	public AlertaDeCarroEmSituacaoIrregularParaUsuarioDto(AlertaDeCarroEmSituacaoIrregularParaUsuario alerta) {
		super(alerta);
		this.carroEmSituacaoIrregularDto=new CarroEmSituacaoIrregularDto(alerta.getCarroEmSituacaoIrregular());
	}

	public CarroEmSituacaoIrregularDto getCarroEmSituacaoIrregular() {
		return carroEmSituacaoIrregularDto;
	}
	
}
