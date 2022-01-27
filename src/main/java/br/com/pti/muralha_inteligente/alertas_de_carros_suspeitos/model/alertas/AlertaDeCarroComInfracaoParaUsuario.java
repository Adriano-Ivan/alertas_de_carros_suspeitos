package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroComInfracaoParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="alertas_de_carros_com_infracao_para_usuarios")
public class AlertaDeCarroComInfracaoParaUsuario extends AlertaParaUsuario{

	@ManyToOne(fetch=FetchType.LAZY)
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
}
