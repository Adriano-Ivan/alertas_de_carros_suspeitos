package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.TarefaParaFazer;

public interface TarefaParaFazerRepository extends
	JpaRepository<TarefaParaFazer, Long>{

}
