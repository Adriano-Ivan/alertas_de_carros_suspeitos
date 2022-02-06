package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroEmSituacaoIrregularDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroRoubadoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroEmSituacaoIrregularForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.enumerator.MedidaAdministrativa;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@Entity
@Table(name="carros_em_situacao_irregular")
public class CarroEmSituacaoIrregular extends Carro {
	
	@Enumerated(EnumType.STRING)
	private MedidaAdministrativa medidaAdministrativa;
	
	public CarroEmSituacaoIrregular() {}

	public CarroEmSituacaoIrregular(CarroEmSituacaoIrregularForm veiculoForm, Zona zona,
			Usuario usuarioInsersor) {
		super(veiculoForm,zona,usuarioInsersor);
		this.medidaAdministrativa=veiculoForm.getMedidaAdministrativa();
	}

	public MedidaAdministrativa getMedidaAdministrativa() {
		return medidaAdministrativa;
	}

	public void setMedidaAdministrativa(MedidaAdministrativa medidaAdministrativa) {
		this.medidaAdministrativa = medidaAdministrativa;
	}
	
	public static Page<CarroEmSituacaoIrregularDto> converter(Page<CarroEmSituacaoIrregular> veiculosEmSituacaoIrregular) {
		return veiculosEmSituacaoIrregular.map(CarroEmSituacaoIrregularDto::new);
	}

	public static CarroEmSituacaoIrregularDto converter(CarroEmSituacaoIrregular veiculoEmSituacaoIrregular) {
		// TODO Auto-generated method stub
		return new CarroEmSituacaoIrregularDto(veiculoEmSituacaoIrregular);
	}

	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha", "medida administrativa: "+medidaAdministrativa);
	}

}
