package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;

@Entity
@Table(name="alertas_de_carros_roubados_para_usuarios")
public class AlertaDeCarroRoubadoParaUsuario extends Alerta {

	@ManyToOne(fetch=FetchType.LAZY)
	private CarroRoubado carroRoubado;
	
	public AlertaDeCarroRoubadoParaUsuario() {}

	public CarroRoubado getCarroRoubado() {
		return carroRoubado;
	}

	public void setCarroRoubado(CarroRoubado carroRoubado) {
		this.carroRoubado = carroRoubado;
	}
}
