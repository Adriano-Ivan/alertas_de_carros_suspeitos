package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroSuspeitoRepository;

public class CarroSuspeitoForm extends CarroForm{
	
	@NotNull @NotBlank @Size(min=5)
	private String justificativa;
	
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	public CarroSuspeito converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		return new CarroSuspeito(this,super.montarZona(zonaRepository),
				super.montarUsuarioInsersor(usuarioRepository));
		
	}
	
	public CarroSuspeito atualizar(Long id, CarroSuspeitoRepository veiculoSuspeitoRepository,
			ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		CarroSuspeito veiculo = veiculoSuspeitoRepository.getById(id);
		
		veiculo.setZona(super.montarZona(zonaRepository));
		veiculo.setUltimoUsuarioEditor(super.montarUsuarioEditor(usuarioRepository));
		//veiculo.setUsuarioInsersor(super.montarUsuarioInsersor(usuarioRepository));
		super.atualizar(veiculo);

		veiculo.setJustificativa(justificativa);
		
		
		return veiculo;
	}
	
}
