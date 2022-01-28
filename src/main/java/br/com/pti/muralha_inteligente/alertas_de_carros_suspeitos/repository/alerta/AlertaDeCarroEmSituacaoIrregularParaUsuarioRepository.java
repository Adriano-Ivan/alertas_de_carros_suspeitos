package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroEmSituacaoIrregularParaUsuario;

public interface AlertaDeCarroEmSituacaoIrregularParaUsuarioRepository extends
	JpaRepository<AlertaDeCarroEmSituacaoIrregularParaUsuario, Long>{

	Page<AlertaDeCarroEmSituacaoIrregularParaUsuario> findByPlacaAndNomeDeUsuario(String placa, String nomeDeUsuario,
			Pageable paginacao);

	Page<AlertaDeCarroEmSituacaoIrregularParaUsuario> findByPlaca(String placa, Pageable paginacao);

	Page<AlertaDeCarroEmSituacaoIrregularParaUsuario> findByNomeDeUsuario(String nomeDeUsuario, Pageable paginacao);

}
