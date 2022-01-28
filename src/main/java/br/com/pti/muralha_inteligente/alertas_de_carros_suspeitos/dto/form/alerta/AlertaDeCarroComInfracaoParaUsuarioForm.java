package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroComInfracaoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta.AlertaDeCarroComInfracaoParaUsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroComInfracaoRepository;

public class AlertaDeCarroComInfracaoParaUsuarioForm extends AlertaParaUsuarioForm{

	@NotNull
	private Long idCarroComInfracao;

	public Long getIdCarroComInfracao() {
		return idCarroComInfracao;
	}

	public void setIdCarroComInfracao(Long idCarroComInfracao) {
		this.idCarroComInfracao = idCarroComInfracao;
	}
	
	public CarroComInfracao encontrarCarroComInfracao(CarroComInfracaoRepository
			carroComInfracaoRepository) {
		Optional<CarroComInfracao> carroComInfracaoOpt = carroComInfracaoRepository.findById(idCarroComInfracao);
		CarroComInfracao carroComInfracao=carroComInfracaoOpt.orElse(null);
		
		return carroComInfracao;
	}
	
	private boolean validarCarroComInfracao(CarroComInfracaoRepository
			carroComInfracaoRepository) {
		CarroComInfracao carroComInfracao=encontrarCarroComInfracao(carroComInfracaoRepository);
		
		if(carroComInfracao==null) {
			return false;
		}
		return true;
	}
	
	public boolean validarCarroComInfracaoEusuario(CarroComInfracaoRepository
			carroComInfracaoRepository,UsuarioRepository usuarioRepository ) {
		return validarCarroComInfracao(carroComInfracaoRepository) && validarUsuario(usuarioRepository);
	}
	public AlertaDeCarroComInfracaoParaUsuario converter(CarroComInfracaoRepository 
			carroComInfracaoRepository, UsuarioRepository usuarioRepository) {
		return new AlertaDeCarroComInfracaoParaUsuario(this,super.encontrarUsuario(usuarioRepository),
				encontrarCarroComInfracao(carroComInfracaoRepository));		
	}
	
	public  AlertaDeCarroComInfracaoParaUsuario atualizar(Long id,
			AlertaDeCarroComInfracaoParaUsuarioRepository 
			alertaDeCarroComInfracaoRepository,
			CarroComInfracaoRepository carroComInfracaoRepository,
			UsuarioRepository usuarioRepository) {
		AlertaDeCarroComInfracaoParaUsuario alerta = alertaDeCarroComInfracaoRepository.getById(id);
		
		CarroComInfracao carroComInfracao = encontrarCarroComInfracao(carroComInfracaoRepository);
		alerta.setCarroComInfracao(carroComInfracao);
		super.atualizar(alerta, usuarioRepository, carroComInfracao);
		
		return alerta;
	}
}
