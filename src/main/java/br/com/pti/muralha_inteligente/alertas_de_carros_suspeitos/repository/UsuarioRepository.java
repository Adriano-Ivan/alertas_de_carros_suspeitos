package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

public interface UsuarioRepository extends
	JpaRepository<Usuario, Long>{

}
