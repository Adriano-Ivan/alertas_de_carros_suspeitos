package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroRoubadoParaUsuario;

public interface AlertaDeCarroRoubadoParaUsuarioRepository extends
	JpaRepository<AlertaDeCarroRoubadoParaUsuario, Long>{

	Page<AlertaDeCarroRoubadoParaUsuario> findByPlacaAndNomeDeUsuario(String placa, String nomeDeUsuario,
			Pageable paginacao);

	Page<AlertaDeCarroRoubadoParaUsuario> findByPlaca(String placa, Pageable paginacao);

	Page<AlertaDeCarroRoubadoParaUsuario> findByNomeDeUsuario(String nomeDeUsuario, Pageable paginacao);

}
