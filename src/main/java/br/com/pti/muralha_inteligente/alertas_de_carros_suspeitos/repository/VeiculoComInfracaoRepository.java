package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoComInfracao;

public interface VeiculoComInfracaoRepository extends
	JpaRepository<VeiculoComInfracao, Long>{

	Page<VeiculoComInfracao> findByPlaca(String placa, Pageable paginacao);

}
