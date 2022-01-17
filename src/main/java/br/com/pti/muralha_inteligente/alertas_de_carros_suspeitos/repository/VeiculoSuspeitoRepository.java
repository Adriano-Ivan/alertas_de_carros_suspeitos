package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.Veiculo;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;

@Repository
public interface VeiculoSuspeitoRepository extends
	JpaRepository<VeiculoSuspeito, Long> {

	@Query(value="select * from veiculos_suspeitos order by momento_do_alerta DESC",
			nativeQuery=true)
	List<VeiculoSuspeito> findAllOrderByMomentoDoAlerta();
}
