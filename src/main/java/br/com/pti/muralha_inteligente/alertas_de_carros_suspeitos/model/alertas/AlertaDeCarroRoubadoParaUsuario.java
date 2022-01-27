package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroRoubadoParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroSuspeitoParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="alertas_de_carros_roubados_para_usuarios")
public class AlertaDeCarroRoubadoParaUsuario extends AlertaParaUsuario {

	@ManyToOne(fetch=FetchType.LAZY)
	private CarroRoubado carroRoubado;
	
	public AlertaDeCarroRoubadoParaUsuario() {}

	public AlertaDeCarroRoubadoParaUsuario(AlertaDeCarroRoubadoParaUsuarioForm alertaDeCarroRoubadoParaUsuarioForm,
			Usuario usuario, CarroRoubado carroRoubado) {
		super(alertaDeCarroRoubadoParaUsuarioForm,usuario,carroRoubado);
		this.carroRoubado=carroRoubado;
	}

	public CarroRoubado getCarroRoubado() {
		return carroRoubado;
	}

	public void setCarroRoubado(CarroRoubado carroRoubado) {
		this.carroRoubado = carroRoubado;
	}
}
