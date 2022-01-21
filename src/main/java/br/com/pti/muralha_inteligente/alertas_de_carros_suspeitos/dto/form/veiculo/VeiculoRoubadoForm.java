package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoRoubadoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoSuspeitoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class VeiculoRoubadoForm extends VeiculoForm {

	@NotBlank @NotNull @Size(min=2)
	private String localDoRoubo;

	public String getLocalDoRoubo() {
		return localDoRoubo;
	}

	public void setLocalDoRoubo(String localDoRoubo) {
		this.localDoRoubo = localDoRoubo;
	}
	
	public VeiculoRoubado converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		return new VeiculoRoubado(this,super.montarZona(zonaRepository),
				super.montarUsuarioEditor(usuarioRepository), super.montarUsuarioInsersor(usuarioRepository));
	}
	
	public VeiculoRoubado atualizar(Long id, VeiculoRoubadoRepository veiculoRoubadoRepository,
			ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		VeiculoRoubado veiculo = veiculoRoubadoRepository.getById(id);
		
		veiculo.setZona(super.definirZona(zonaRepository));
		veiculo.setUltimoUsuarioEditor(super.definirUsuarioEditor(usuarioRepository));
		veiculo.setUsuarioInsersor(super.definirUsuarioInsersor(usuarioRepository));
		super.atualizar(veiculo,zonaRepository, usuarioRepository);

		veiculo.setLocalDoRoubo(localDoRoubo);
		
		return veiculo;
	}
	
}
