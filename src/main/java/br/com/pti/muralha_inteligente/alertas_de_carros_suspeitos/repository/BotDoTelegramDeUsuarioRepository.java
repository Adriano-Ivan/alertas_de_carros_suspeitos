package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.BotDoTelegramDeUsuario;

public interface BotDoTelegramDeUsuarioRepository extends
 	JpaRepository<BotDoTelegramDeUsuario, Long>{

	Page<BotDoTelegramDeUsuario> findByNomeDoUsuarioAndNomeDoBot(String nomeDoUsuario,
			String denominacaoDoBotDoTelegram, Pageable paginacao);

	Page<BotDoTelegramDeUsuario> findByNomeDoUsuario(String nomeDoUsuario, Pageable paginacao);

	Page<BotDoTelegramDeUsuario> findByNomeDoBot(String denominacaoDoBotDoTelegram,
			Pageable paginacao);

}
