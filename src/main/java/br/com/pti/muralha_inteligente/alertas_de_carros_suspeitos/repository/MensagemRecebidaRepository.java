package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemRecebida;

public interface MensagemRecebidaRepository extends
	JpaRepository<MensagemRecebida, Long>{

	Page<MensagemRecebida> findByMensagem(String mensagem, Pageable paginacao);

}
