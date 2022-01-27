package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro;

import java.util.Optional;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.enumerator.GravidadeDaInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroComInfracaoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroRoubadoRepository;

public class CarroComInfracaoForm extends CarroForm {
	
	private GravidadeDaInfracao gravidadeDaInfracao;

	public GravidadeDaInfracao getGravidadeDaInfracao() {
		return gravidadeDaInfracao;
	}

	public void setGravidadeDaInfracao(GravidadeDaInfracao gravidadeDaInfracao) {
		this.gravidadeDaInfracao = gravidadeDaInfracao;
	}
	
	public CarroComInfracao converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		return new CarroComInfracao(this,super.montarZona(zonaRepository),
				 super.montarUsuarioInsersor(usuarioRepository));
	}
	
	public CarroComInfracao atualizar(Long id, CarroComInfracaoRepository veiculoComInfracaoRepository,
			ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		CarroComInfracao veiculo = veiculoComInfracaoRepository.getById(id);

		veiculo.setZona(super.montarZona(zonaRepository));
		veiculo.setUltimoUsuarioEditor(super.montarUsuarioEditor(usuarioRepository));
		
		super.atualizar(veiculo);

		veiculo.setGravidadeDaInfracao(gravidadeDaInfracao);
		
		return veiculo;
	}
}
