package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta.AlertaDeCarroComInfracaoParaUsuarioDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroComInfracaoParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="alertas_de_carros_com_infracao_para_usuarios")
public class AlertaDeCarroComInfracaoParaUsuario extends AlertaParaUsuario{

	@ManyToOne(fetch=FetchType.EAGER)
	private CarroComInfracao carroComInfracao;
	
	public AlertaDeCarroComInfracaoParaUsuario() {}

	public AlertaDeCarroComInfracaoParaUsuario(
			AlertaDeCarroComInfracaoParaUsuarioForm alertaDeCarroComInfracaoParaUsuarioForm, Usuario usuario,
			CarroComInfracao carroComInfracao) {
		super(alertaDeCarroComInfracaoParaUsuarioForm,usuario,carroComInfracao);
		this.carroComInfracao=carroComInfracao;
	}

	public CarroComInfracao getCarroComInfracao() {
		return carroComInfracao;
	}

	public void setCarroComInfracao(CarroComInfracao carroComInfracao) {
		this.carroComInfracao = carroComInfracao;
	}

	public static Page<AlertaDeCarroComInfracaoParaUsuarioDto> converter(
			Page<AlertaDeCarroComInfracaoParaUsuario> alertas) {
		return alertas.map(AlertaDeCarroComInfracaoParaUsuarioDto::new);
	}

	public static AlertaDeCarroComInfracaoParaUsuarioDto converter(AlertaDeCarroComInfracaoParaUsuario
			alertaDeCarroComInfracaoParaUsuario) {
		return new AlertaDeCarroComInfracaoParaUsuarioDto(alertaDeCarroComInfracaoParaUsuario);
	}
	
	@Override
	public String toString() {
		return super.toString().replace("classe_filha", this.getCarroComInfracao().toString());
	}
}
