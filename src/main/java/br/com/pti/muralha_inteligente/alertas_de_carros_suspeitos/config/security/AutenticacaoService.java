package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements
	UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	// O parâmetro está como username, mas a busca do repository será um OR por username ou por email
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.encontrarPorEmailOuNomeDeUsuario(usernameOrEmail);
		
		if(usuario.isPresent()) {
			return usuario.get();
		}
		
		throw new UsernameNotFoundException("Dados inválidos.");
	}
}
