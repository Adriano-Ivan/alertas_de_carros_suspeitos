package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroEmSituacaoIrregularParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta.AlertaDeCarroEmSituacaoIrregularParaUsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroEmSituacaoIrregularRepository;

public class AlertaDeCarroEmSituacaoIrregularParaUsuarioForm extends AlertaParaUsuarioForm{

	@NotNull
	private Long idCarroEmSituacaoIrregular;

	public Long getIdCarroEmSituacaoIrregular() {
		return idCarroEmSituacaoIrregular;
	}

	public void setIdCarroEmSituacaoIrregular(Long idCarroEmSituacaoIrregular) {
		this.idCarroEmSituacaoIrregular = idCarroEmSituacaoIrregular;
	}
	
	public CarroEmSituacaoIrregular encontrarCarroEmSituacaoIrregular(CarroEmSituacaoIrregularRepository 
			carroEmSituacaoIrregularRepository) {
		Optional<CarroEmSituacaoIrregular> carroEmSituacaoIrregularOpt = carroEmSituacaoIrregularRepository
				.findById(idCarroEmSituacaoIrregular);
		
		CarroEmSituacaoIrregular carroEmSituacaoIrregular = carroEmSituacaoIrregularOpt.orElse(null);
		
		return carroEmSituacaoIrregular;
	}
	
	public boolean validarCarroEmSituacaoIrregular(CarroEmSituacaoIrregularRepository
			carroEmSituacaoIrregularRepository) {
		CarroEmSituacaoIrregular carroEmSituacaoIrregular = encontrarCarroEmSituacaoIrregular(carroEmSituacaoIrregularRepository);
		
		if(carroEmSituacaoIrregular==null) {
			return false;
		}
		
		return true;
	}
	
	public AlertaDeCarroEmSituacaoIrregularParaUsuario converter(CarroEmSituacaoIrregularRepository
			carroEmSituacaoIrregularRepository,  UsuarioRepository usuarioRepository) {
		return new AlertaDeCarroEmSituacaoIrregularParaUsuario(this, super
				.encontrarUsuario(usuarioRepository), encontrarCarroEmSituacaoIrregular(carroEmSituacaoIrregularRepository));
	}
	
	public AlertaDeCarroEmSituacaoIrregularParaUsuario atualizar(Long id,
			AlertaDeCarroEmSituacaoIrregularParaUsuarioRepository 
			alertaDeCarroEmSituacaoIrregularRepository,
			CarroEmSituacaoIrregularRepository carroEmSituacaoIrregularRepository,
			UsuarioRepository usuarioRepository) {
		AlertaDeCarroEmSituacaoIrregularParaUsuario alerta = alertaDeCarroEmSituacaoIrregularRepository.getById(id);
		
		CarroEmSituacaoIrregular carroEmSituacaoIrregular = encontrarCarroEmSituacaoIrregular(carroEmSituacaoIrregularRepository);
		alerta.setCarroEmSituacaoIrregular(carroEmSituacaoIrregular);
		super.atualizar(alerta, usuarioRepository, carroEmSituacaoIrregular);
		
		return alerta;
	}
}
