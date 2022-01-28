package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroSuspeitoParaUsuario;

public interface AlertaDeCarroSuspeitoParaUsuarioRepository extends
	JpaRepository<AlertaDeCarroSuspeitoParaUsuario, Long>{

	Page<AlertaDeCarroSuspeitoParaUsuario> findByPlacaAndNomeDeUsuario(String placa, String nomeDeUsuario,
			Pageable paginacao);

	Page<AlertaDeCarroSuspeitoParaUsuario> findByPlaca(String placa, Pageable paginacao);

	Page<AlertaDeCarroSuspeitoParaUsuario> findByNomeDeUsuario(String nomeDeUsuario, Pageable paginacao);

}
