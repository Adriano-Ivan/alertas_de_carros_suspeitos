package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroEmSituacaoIrregularParaUsuario;

public interface AlertaDeCarroEmSituacaoIrregularParaUsuarioRepository extends
	JpaRepository<AlertaDeCarroEmSituacaoIrregularParaUsuario, Long>{

}
