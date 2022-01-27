package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.mensagem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemEnviada;

public interface MensagemEnviadaRepository extends
	JpaRepository<MensagemEnviada, Long>{

	Page<MensagemEnviada> findByMensagem(String mensagem, Pageable paginacao);

}
