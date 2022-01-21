package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator.MedidaAdministrativa;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoEmSituacaoIrregularRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoRoubadoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class VeiculoEmSituacaoIrregularForm extends VeiculoForm{
	 
	private MedidaAdministrativa medidaAdministrativa;

	public MedidaAdministrativa getMedidaAdministrativa() {
		return medidaAdministrativa;
	}

	public void setMedidaAdministrativa(MedidaAdministrativa medidaAdministrativa) {
		this.medidaAdministrativa = medidaAdministrativa;
	}
	
	public VeiculoEmSituacaoIrregular converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		return new VeiculoEmSituacaoIrregular(this,super.montarZona(zonaRepository),
				super.montarUsuarioEditor(usuarioRepository), super.montarUsuarioInsersor(usuarioRepository));
	}
	
	public VeiculoEmSituacaoIrregular atualizar(Long id, VeiculoEmSituacaoIrregularRepository veiculoEmSituacaoIrregularRepository,
			ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		VeiculoEmSituacaoIrregular veiculo = veiculoEmSituacaoIrregularRepository.getById(id);
		
		veiculo.setZona(super.definirZona(zonaRepository));
		veiculo.setUltimoUsuarioEditor(super.definirUsuarioEditor(usuarioRepository));
		veiculo.setUsuarioInsersor(super.definirUsuarioInsersor(usuarioRepository));
		super.atualizar(veiculo,zonaRepository, usuarioRepository);

		veiculo.setMedidaAdministrativa(medidaAdministrativa);
		
		return veiculo;
	}
	
}
