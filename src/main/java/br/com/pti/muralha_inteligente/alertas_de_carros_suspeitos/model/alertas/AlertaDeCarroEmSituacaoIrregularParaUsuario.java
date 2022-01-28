package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta.AlertaDeCarroEmSituacaoIrregularParaUsuarioDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroEmSituacaoIrregularParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroRoubadoParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="alertas_de_carros_em_situacao_irregular_para_usuarios")
public class AlertaDeCarroEmSituacaoIrregularParaUsuario extends AlertaParaUsuario{

	@ManyToOne(fetch=FetchType.LAZY)
	private CarroEmSituacaoIrregular carroEmSituacaoIrregular;
	
	public AlertaDeCarroEmSituacaoIrregularParaUsuario() {}

	public AlertaDeCarroEmSituacaoIrregularParaUsuario(AlertaDeCarroEmSituacaoIrregularParaUsuarioForm alertaDeCarroEmSituacaoIrregularParaUsuarioForm,
			Usuario usuario, CarroEmSituacaoIrregular carroEmSituacaoIrregular) {
		super(alertaDeCarroEmSituacaoIrregularParaUsuarioForm,usuario,carroEmSituacaoIrregular);
		this.carroEmSituacaoIrregular=carroEmSituacaoIrregular;
	}
	
	public CarroEmSituacaoIrregular getCarroEmSituacaoIrregular() {
		return carroEmSituacaoIrregular;
	}

	public void setCarroEmSituacaoIrregular(CarroEmSituacaoIrregular carroEmSituacaoIrregular) {
		this.carroEmSituacaoIrregular = carroEmSituacaoIrregular;
	}

	public static Page<AlertaDeCarroEmSituacaoIrregularParaUsuarioDto> converter(
			Page<AlertaDeCarroEmSituacaoIrregularParaUsuario> alertas) {
		return alertas.map(AlertaDeCarroEmSituacaoIrregularParaUsuarioDto::new);
	}

	public static AlertaDeCarroEmSituacaoIrregularParaUsuarioDto converter(
			AlertaDeCarroEmSituacaoIrregularParaUsuario alertaDeCarroEmSituacaoIrregularParaUsuario) {
		return new AlertaDeCarroEmSituacaoIrregularParaUsuarioDto(alertaDeCarroEmSituacaoIrregularParaUsuario);
	}
}
