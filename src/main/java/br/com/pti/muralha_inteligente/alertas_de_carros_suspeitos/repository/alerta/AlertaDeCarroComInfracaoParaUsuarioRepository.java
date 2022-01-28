package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroComInfracaoParaUsuario;

public interface AlertaDeCarroComInfracaoParaUsuarioRepository extends
	JpaRepository<AlertaDeCarroComInfracaoParaUsuario, Long>{

	Page<AlertaDeCarroComInfracaoParaUsuario> findByPlacaAndNomeDeUsuario(String placa, String nomeDeUsuario,
			Pageable paginacao);

	Page<AlertaDeCarroComInfracaoParaUsuario> findByPlaca(String placa, Pageable paginacao);

	Page<AlertaDeCarroComInfracaoParaUsuario> findByNomeDeUsuario(String nomeDeUsuario, Pageable paginacao);

}
