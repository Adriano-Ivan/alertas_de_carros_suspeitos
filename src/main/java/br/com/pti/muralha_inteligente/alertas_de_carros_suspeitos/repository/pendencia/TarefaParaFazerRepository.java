package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.pendencia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.TarefaParaFazer;

public interface TarefaParaFazerRepository extends
	JpaRepository<TarefaParaFazer, Long>{

	Page<TarefaParaFazer> findByDescricao(String descricao, Pageable paginacao);

}
