package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroRoubadoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroSuspeitoRepository;

public class CarroRoubadoForm extends CarroForm {

	@NotBlank @NotNull @Size(min=2)
	private String localDoRoubo;

	public String getLocalDoRoubo() {
		return localDoRoubo;
	}

	public void setLocalDoRoubo(String localDoRoubo) {
		this.localDoRoubo = localDoRoubo;
	}
	
	public CarroRoubado converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		return new CarroRoubado(this,super.montarZona(zonaRepository),
				 super.montarUsuarioInsersor(usuarioRepository));
	}
	
	public CarroRoubado atualizar(Long id, CarroRoubadoRepository veiculoRoubadoRepository,
			ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		CarroRoubado veiculo = veiculoRoubadoRepository.getById(id);
		
		veiculo.setZona(super.montarZona(zonaRepository));
		veiculo.setUltimoUsuarioEditor(super.montarUltimoUsuarioEditor(usuarioRepository));
		//veiculo.setUsuarioInsersor(super.montarUsuarioInsersor(usuarioRepository));
		super.atualizar(veiculo);

		veiculo.setLocalDoRoubo(localDoRoubo);
		
		return veiculo;
	}
	
}
