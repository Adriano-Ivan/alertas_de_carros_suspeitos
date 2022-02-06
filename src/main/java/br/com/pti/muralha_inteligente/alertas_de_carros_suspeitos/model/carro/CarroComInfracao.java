package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroComInfracaoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroComInfracaoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.enumerator.GravidadeDaInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="carros_com_infracao")
public class CarroComInfracao extends Carro {

	@Enumerated(EnumType.STRING)
	private GravidadeDaInfracao gravidadeDaInfracao;
	
	public CarroComInfracao() {}
	
	public CarroComInfracao(CarroComInfracaoForm veiculoForm, Zona zona,
			Usuario usuarioInsersor) {
		super(veiculoForm,zona,usuarioInsersor);
		this.gravidadeDaInfracao=veiculoForm.getGravidadeDaInfracao();
	}

	public GravidadeDaInfracao getGravidadeDaInfracao() {
		return this.gravidadeDaInfracao;
	}
	
	public void setGravidadeDaInfracao(GravidadeDaInfracao gdi) {
		this.gravidadeDaInfracao=gdi;
	}
	
	public static CarroComInfracaoDto converter(CarroComInfracao veiculo) {
		return new CarroComInfracaoDto(veiculo);
	}

	public static Page<CarroComInfracaoDto> converter(Page<CarroComInfracao> veiculosSuspeitos) {
		return veiculosSuspeitos.map(CarroComInfracaoDto::new);
	}
	
	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha","gravidade da infração: "+gravidadeDaInfracao);
	}

}
