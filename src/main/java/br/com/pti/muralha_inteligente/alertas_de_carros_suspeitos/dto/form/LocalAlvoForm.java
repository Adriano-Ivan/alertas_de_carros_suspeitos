package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.form_util.MontadorEValidadorDeUsuarioEzona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.LocalAlvo;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.LocalAlvoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class LocalAlvoForm extends MontadorEValidadorDeUsuarioEzona {

	@NotNull @NotBlank @Size(min=2)
	private String local;


	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public boolean validarZonaAssociadaEUsuarioInsersor(ZonaRepository zonaRepository,UsuarioRepository usuarioRepository) {
		return validarUsuarioInsersor(usuarioRepository) && validarZona(zonaRepository);
	}

	public boolean validarZonaAssociadaEultimoUsuarioEditor(ZonaRepository zonaRepository,UsuarioRepository usuarioRepository) {
		return validarUltimoUsuarioEditor(usuarioRepository) && validarZona(zonaRepository);
	}
	
	public LocalAlvo converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {	
		return new LocalAlvo(local,montarUsuarioInsersor(usuarioRepository),montarZona(idZona,zonaRepository));
	}
	
	public LocalAlvo atualizar(Long id, LocalAlvoRepository localAlvoRepository,
			ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		LocalAlvo localAlvo=localAlvoRepository.getById(id);
		
		localAlvo.setLocal(local);
		localAlvo.setUltimoUsuarioEditor(montarUltimoUsuarioEditor( usuarioRepository));
		localAlvo.setZonaAssociada(montarZona(idZona, zonaRepository));
		localAlvo.setUpdatedAt(LocalDateTime.now());
		
		return localAlvo;
	}
}
