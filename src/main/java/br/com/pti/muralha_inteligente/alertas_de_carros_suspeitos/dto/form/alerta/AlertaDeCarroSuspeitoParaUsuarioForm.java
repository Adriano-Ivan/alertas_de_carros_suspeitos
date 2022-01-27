package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroSuspeitoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta.AlertaDeCarroSuspeitoParaUsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroSuspeitoRepository;

public class AlertaDeCarroSuspeitoParaUsuarioForm extends AlertaParaUsuarioForm {

	@NotNull
	private Long idCarroSuspeito;

	public Long getIdVeiculoSuspeito() {
		return idCarroSuspeito;
	}

	public void setIdCarroSuspeito(Long idCarroSuspeito) {
		this.idCarroSuspeito = idCarroSuspeito;
	}

	private CarroSuspeito encontrarCarroSuspeito(CarroSuspeitoRepository carroSuspeitoRepository) {
		Optional<CarroSuspeito> carroSuspeitoOpt = carroSuspeitoRepository.findById(idCarroSuspeito);

		CarroSuspeito carroSuspeito = carroSuspeitoOpt.orElse(null);

		return carroSuspeito;
	}

	public boolean validarCarroSuspeito(CarroSuspeitoRepository carroSuspeitoRepository) {
		CarroSuspeito carroSuspeito = encontrarCarroSuspeito(carroSuspeitoRepository);
		
		if(carroSuspeito == null) {
			return false;
		}
		
		return true;
	}
	
	public AlertaDeCarroSuspeitoParaUsuario converter(CarroSuspeitoRepository carroSuspeitoRepository,
			UsuarioRepository usuarioRepository) {
		return new AlertaDeCarroSuspeitoParaUsuario(this,super.encontrarUsuario(usuarioRepository),
				encontrarCarroSuspeito(carroSuspeitoRepository));
	}
	
	public AlertaDeCarroSuspeitoParaUsuario atualizar(Long id,
			AlertaDeCarroSuspeitoParaUsuarioRepository alertaDeCarroSuspeitoParaUsuarioRepository,
			CarroSuspeitoRepository carroSuspeitoRepository,
			UsuarioRepository usuarioRepository) {
		AlertaDeCarroSuspeitoParaUsuario alerta = alertaDeCarroSuspeitoParaUsuarioRepository.getById(id);
		
		CarroSuspeito carroSuspeito = encontrarCarroSuspeito(carroSuspeitoRepository);
		alerta.setCarroSuspeito(carroSuspeito);
		super.atualizar(alerta, usuarioRepository, carroSuspeito);
		
		return alerta;
	}
}
