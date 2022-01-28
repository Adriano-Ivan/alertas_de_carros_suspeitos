package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta.AlertaDeCarroSuspeitoParaUsuarioDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroSuspeitoParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="alertas_de_carros_suspeitos_para_usuarios")
public class AlertaDeCarroSuspeitoParaUsuario extends AlertaParaUsuario {

	@ManyToOne(fetch=FetchType.LAZY)
	private CarroSuspeito carroSuspeito;
	
	public AlertaDeCarroSuspeitoParaUsuario() {
		
	}

	public AlertaDeCarroSuspeitoParaUsuario(AlertaDeCarroSuspeitoParaUsuarioForm alertaDeCarroSuspeitoParaUsuarioForm,
			Usuario usuario, CarroSuspeito carroSuspeito) {
		super(alertaDeCarroSuspeitoParaUsuarioForm,usuario,carroSuspeito);
		this.carroSuspeito=carroSuspeito;
	}

	public CarroSuspeito getCarroSuspeito() {
		return carroSuspeito;
	}

	public void setCarroSuspeito(CarroSuspeito carroSuspeito) {
		this.carroSuspeito = carroSuspeito;
	}

	public static Page<AlertaDeCarroSuspeitoParaUsuarioDto> converter(Page<AlertaDeCarroSuspeitoParaUsuario> alertas) {
		return alertas.map(AlertaDeCarroSuspeitoParaUsuarioDto::new);
	}

	public static AlertaDeCarroSuspeitoParaUsuarioDto converter(AlertaDeCarroSuspeitoParaUsuario alertaDeCarroSuspeitoParaUsuario) {
		return new AlertaDeCarroSuspeitoParaUsuarioDto(alertaDeCarroSuspeitoParaUsuario);
	}
	
	
}
