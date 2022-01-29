package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta.AlertaDeCarroRoubadoParaUsuarioDto;
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

	public static Page<AlertaDeCarroRoubadoParaUsuarioDto> converter(Page<AlertaDeCarroRoubadoParaUsuario> alertas) {
		return alertas.map(AlertaDeCarroRoubadoParaUsuarioDto::new);
	}

	public static AlertaDeCarroRoubadoParaUsuarioDto converter(AlertaDeCarroRoubadoParaUsuario alertaDeCarroRoubadoParaUsuario) {
		return new AlertaDeCarroRoubadoParaUsuarioDto(alertaDeCarroRoubadoParaUsuario);
	}
	
	@Override
	public String toString() {
		return super.toString().replace("classe_filha", this.getCarroRoubado().toString());
	}
}
