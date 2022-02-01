package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.enumerator.MedidaAdministrativa;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroEmSituacaoIrregularRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroRoubadoRepository;

public class CarroEmSituacaoIrregularForm extends CarroForm{
	 
	private MedidaAdministrativa medidaAdministrativa;

	public MedidaAdministrativa getMedidaAdministrativa() {
		return medidaAdministrativa;
	}

	public void setMedidaAdministrativa(MedidaAdministrativa medidaAdministrativa) {
		this.medidaAdministrativa = medidaAdministrativa;
	}
	
	public CarroEmSituacaoIrregular converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		return new CarroEmSituacaoIrregular(this,super.montarZona(zonaRepository),
				 super.montarUsuarioInsersor(usuarioRepository));
	}
	
	public CarroEmSituacaoIrregular atualizar(Long id, CarroEmSituacaoIrregularRepository veiculoEmSituacaoIrregularRepository,
			ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		CarroEmSituacaoIrregular veiculo = veiculoEmSituacaoIrregularRepository.getById(id);
		
		veiculo.setZona(super.montarZona(zonaRepository));
		veiculo.setUltimoUsuarioEditor(super.montarUltimoUsuarioEditor(usuarioRepository));
		
		super.atualizar(veiculo);

		veiculo.setMedidaAdministrativa(medidaAdministrativa);
		
		return veiculo;
	}
	
}
