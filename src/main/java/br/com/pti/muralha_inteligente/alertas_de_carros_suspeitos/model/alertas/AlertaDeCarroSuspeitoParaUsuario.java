package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;

@Entity
@Table(name="alertas_de_carros_suspeitos_para_usuarios")
public class AlertaDeCarroSuspeitoParaUsuario extends Alerta {

	@ManyToOne(fetch=FetchType.LAZY)
	private CarroSuspeito carroSuspeito;
	
	public AlertaDeCarroSuspeitoParaUsuario() {
		
	}

	public CarroSuspeito getCarroSuspeito() {
		return carroSuspeito;
	}

	public void setCarroSuspeito(CarroSuspeito carroSuspeito) {
		this.carroSuspeito = carroSuspeito;
	}
	
	
}
