package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo;

import java.util.Optional;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator.GravidadeDaInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoComInfracaoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoRoubadoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class VeiculoComInfracaoForm extends VeiculoForm {
	
	private GravidadeDaInfracao gravidadeDaInfracao;

	public GravidadeDaInfracao getGravidadeDaInfracao() {
		return gravidadeDaInfracao;
	}

	public void setGravidadeDaInfracao(GravidadeDaInfracao gravidadeDaInfracao) {
		this.gravidadeDaInfracao = gravidadeDaInfracao;
	}
	
	public VeiculoComInfracao converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		return new VeiculoComInfracao(this,super.montarZona(zonaRepository),
				 super.montarUsuarioInsersor(usuarioRepository));
	}
	
	public VeiculoComInfracao atualizar(Long id, VeiculoComInfracaoRepository veiculoComInfracaoRepository,
			ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		VeiculoComInfracao veiculo = veiculoComInfracaoRepository.getById(id);

		veiculo.setZona(super.montarZona(zonaRepository));
		veiculo.setUltimoUsuarioEditor(super.montarUsuarioEditor(usuarioRepository));
		//veiculo.setUsuarioInsersor(super.montarUsuarioInsersor(usuarioRepository));
		super.atualizar(veiculo,zonaRepository);

		veiculo.setGravidadeDaInfracao(gravidadeDaInfracao);
		
		return veiculo;
	}
}
