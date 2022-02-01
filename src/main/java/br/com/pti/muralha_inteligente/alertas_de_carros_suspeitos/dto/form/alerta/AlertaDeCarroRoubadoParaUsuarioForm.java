package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroRoubadoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta.AlertaDeCarroRoubadoParaUsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroRoubadoRepository;

public class AlertaDeCarroRoubadoParaUsuarioForm extends AlertaParaUsuarioForm{

	@NotNull
	private Long idCarroRoubado;

	public Long getIdCarroRoubado() {
		return idCarroRoubado;
	}

	public void setIdCarroRoubado(Long idCarroRoubado) {
		this.idCarroRoubado = idCarroRoubado;
	}
	
	public CarroRoubado encontrarCarroRoubado(CarroRoubadoRepository carroRoubadoRepository) {
		Optional<CarroRoubado> carroRoubadoOpt = carroRoubadoRepository.findById(idCarroRoubado);
		CarroRoubado carroRoubado = carroRoubadoOpt.orElse(null);
		
		return carroRoubado;
	}
	
	private boolean validarCarroRoubado(CarroRoubadoRepository carroRoubadoRepository) {
		CarroRoubado carroRoubado = encontrarCarroRoubado(carroRoubadoRepository);
		
		if(carroRoubado == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean validarCarroRoubadoEusuario(CarroRoubadoRepository carroRoubadoRepository,
			UsuarioRepository usuarioRepository) {
		return validarCarroRoubado(carroRoubadoRepository) && validarUsuario(usuarioRepository);
	}

	
	public AlertaDeCarroRoubadoParaUsuario converter(CarroRoubadoRepository carroRoubadoRepository,
			UsuarioRepository usuarioRepository) {
		return new AlertaDeCarroRoubadoParaUsuario(this,super.montarUsuario(usuarioRepository),
				encontrarCarroRoubado(carroRoubadoRepository));
	}
	
	public AlertaDeCarroRoubadoParaUsuario atualizar(Long id,
			AlertaDeCarroRoubadoParaUsuarioRepository alertaDeCarroRoubadoParaUsuarioRepository,
			CarroRoubadoRepository carroRoubadoRepository,
			UsuarioRepository usuarioRepository) {
		AlertaDeCarroRoubadoParaUsuario alerta= alertaDeCarroRoubadoParaUsuarioRepository.getById(id);
		
		CarroRoubado carroRoubado = encontrarCarroRoubado(carroRoubadoRepository);
		alerta.setCarroRoubado(carroRoubado);
		super.atualizar(alerta,usuarioRepository, carroRoubado);
		
		return alerta;
	}
}
