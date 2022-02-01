package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.form_util.MontadorEValidadorDeUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class ZonaForm extends MontadorEValidadorDeUsuario{

	@NotNull @NotBlank @Size(min=2)
	private String zona;
	
	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Zona converter(UsuarioRepository usuarioRepository) {
		return new Zona(this,montarUsuarioInsersor(usuarioRepository));
	}
	
	public Zona atualizar(Long id, ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		Zona zonaObj = zonaRepository.getById(id);

		zonaObj.setUltimoUsuarioEditor(montarUltimoUsuarioEditor(usuarioRepository));
		zonaObj.setZona(zona);
		zonaObj.setUpdatedAt(LocalDateTime.now());
		
		return zonaObj;
	}
}
