package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroRoubadoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroRoubadoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="carros_roubados")
public class CarroRoubado extends Carro {
	
	@Column(length=80)
	private String localDoRoubo;
	
	public CarroRoubado() {}

	public CarroRoubado(CarroRoubadoForm veiculoForm, Zona zona,
			Usuario usuarioInsersor) {
		super(veiculoForm,zona,usuarioInsersor);
		this.localDoRoubo=veiculoForm.getLocalDoRoubo();
	}

	public String getLocalDoRoubo() {
		return localDoRoubo;
	}

	public void setLocalDoRoubo(String localDoRoubo) {
		this.localDoRoubo = localDoRoubo;
	}


	public static Page<CarroRoubadoDto> converter(Page<CarroRoubado> veiculosSuspeitos) {
		return veiculosSuspeitos.map(CarroRoubadoDto::new);
	}

	public static CarroRoubadoDto converter(CarroRoubado veiculoRoubado) {
		// TODO Auto-generated method stub
		return new CarroRoubadoDto(veiculoRoubado);
	}
	
	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha", "local do roubo: "+localDoRoubo);
	}

}
