package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroSuspeitoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="carros_suspeitos")
public class CarroSuspeito extends Carro  {
	
	@Column(columnDefinition = "TEXT")
	private String justificativa;
	
	
	public CarroSuspeito() {}

	public CarroSuspeito(CarroSuspeitoForm veiculoForm, Zona zona, 
			Usuario usuarioInsersor) {
		super(veiculoForm, zona,usuarioInsersor);
		this.justificativa=veiculoForm.getJustificativa();
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	public static Page<CarroSuspeitoDto> converter(Page<CarroSuspeito> veiculosSuspeitos) {
//		return veiculosSuspeitos.stream().map(VeiculoSuspeitoDto::new)
//				.collect(Collectors.toList());
		return veiculosSuspeitos.map(CarroSuspeitoDto::new);
	}

	public static CarroSuspeitoDto converter(CarroSuspeito veiculo) {
		return new CarroSuspeitoDto(veiculo);
	}
	
	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha", "justificativa: "+justificativa);
	}

	
}
