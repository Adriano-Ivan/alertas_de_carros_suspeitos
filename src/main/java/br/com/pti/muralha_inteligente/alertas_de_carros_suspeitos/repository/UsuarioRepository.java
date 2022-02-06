package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

public interface UsuarioRepository extends
	JpaRepository<Usuario, Long>{

	@Query(value="SELECT * FROM usuarios u WHERE u.nome_de_usuario = ?1 or u.email = ?1",
			nativeQuery=true)
	Optional<Usuario> encontrarPorEmailOuNomeDeUsuario(String usernameOrEmail);

	Page<Usuario> findByNomeDeUsuario(String nomeDeUsuario, Pageable paginacao);

}
