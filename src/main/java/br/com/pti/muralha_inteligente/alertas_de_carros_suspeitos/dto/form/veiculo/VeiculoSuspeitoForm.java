package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoSuspeitoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class VeiculoSuspeitoForm extends VeiculoForm{
	
	@NotNull @NotBlank @Size(min=5)
	private String justificativa;
	
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	public VeiculoSuspeito converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		Optional<Zona> zonaOpt = zonaRepository.findById(idZona);
		Zona zona = zonaOpt.orElse(null);
		
		Optional<Usuario> usuarioEditorOpt = usuarioRepository.findById(idUltimoUsuarioEditor);
		Usuario usuarioEditor = usuarioEditorOpt.orElse(null);
		
		Optional<Usuario> usuarioInsersorOpt = usuarioRepository.findById(idUsuarioInsersor);
		Usuario usuarioInsersor = usuarioInsersorOpt.orElse(null);
		
		return new VeiculoSuspeito(this,zona,usuarioEditor,usuarioInsersor);
	}
	
	public VeiculoSuspeito atualizar(Long id, VeiculoSuspeitoRepository veiculoSuspeitoRepository,
			ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		VeiculoSuspeito veiculo = veiculoSuspeitoRepository.getById(id);
		
		Optional<Zona> zonaOpt = zonaRepository.findById(idZona);
		Zona zona = zonaOpt.orElse(null);
		
		Optional<Usuario> usuarioEditorOpt = usuarioRepository.findById(idUltimoUsuarioEditor);
		Usuario usuarioEditor = usuarioEditorOpt.orElse(null);
		
		Optional<Usuario> usuarioInsersorOpt = usuarioRepository.findById(idUsuarioInsersor);
		Usuario usuarioInsersor = usuarioInsersorOpt.orElse(null);
		
		veiculo.setZona(zona);
		veiculo.setUltimoUsuarioEditor(usuarioEditor);
		veiculo.setUsuarioInsersor(usuarioInsersor);
		super.atualizar(veiculo,zona,usuarioEditor,usuarioInsersor);

		veiculo.setJustificativa(justificativa);
		
		
		return veiculo;
	}
	
}
