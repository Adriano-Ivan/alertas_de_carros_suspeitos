package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.form_util.MontadorEValidadorDeUsuarioEzona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.enumerator.TipoAutoridade;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

public class UsuarioForm extends MontadorEValidadorDeUsuarioEzona{

	@NotNull @NotBlank @Size(min=6,message="O campo deve ter no mínimo 6 caracteres.")
	private String nomeDeUsuario;
	
	@NotNull @NotBlank @Email
	private String email;
	
	@NotNull @NotBlank @Size(min=6,message="O campo deve ter no mínimo 6 caracteres.")
	private String senha;
	
	private TipoAutoridade autoridade;
	
	private String token;

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoAutoridade getAutoridade() {
		return autoridade;
	}

	public void setAutoridade(TipoAutoridade autoridade) {
		this.autoridade = autoridade;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	
	public boolean validarZonaAssociadaEUsuarioInsersor(ZonaRepository zonaRepository,UsuarioRepository usuarioRepository) {
		return validarUsuarioInsersor(usuarioRepository) && validarZona(zonaRepository);
	}

	public boolean validarZonaAssociadaEultimoUsuarioEditor(ZonaRepository zonaRepository,UsuarioRepository usuarioRepository) {
		return validarUltimoUsuarioEditor(usuarioRepository) && validarZona(zonaRepository);
	}
	
	public Usuario converter(ZonaRepository zonaRepository, UsuarioRepository usuarioRepository) {
		senha = new BCryptPasswordEncoder().encode(senha);
		return new Usuario(this,montarUsuarioInsersor(usuarioRepository),montarZona(idZona, zonaRepository));
	}
	
	public Usuario atualizar(Long id,UsuarioRepository usuarioRepository,ZonaRepository zonaRepository) {
		Usuario usuario = usuarioRepository.getById(id);
		
		usuario.setZona(montarZona(idZona, zonaRepository));
		usuario.setAutoridade(autoridade);
		usuario.setUltimoUsuarioEditor(montarUltimoUsuarioEditor(usuarioRepository));
		usuario.setUpdatedAt(LocalDateTime.now());
		usuario.setEmail(email);
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		usuario.setNomeDeUsuario(nomeDeUsuario);
		
		if(token!=null) {
			usuario.setToken(token);
		}
		
		return usuario;
	}
}
