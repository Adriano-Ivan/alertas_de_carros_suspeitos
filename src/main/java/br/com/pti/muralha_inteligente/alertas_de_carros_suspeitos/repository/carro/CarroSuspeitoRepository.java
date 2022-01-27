package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.Carro;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;

@Repository
public interface CarroSuspeitoRepository extends
	JpaRepository<CarroSuspeito, Long> {

	@Query(value="select * from veiculos_suspeitos order by momento_do_alerta DESC",
			nativeQuery=true)
	List<CarroSuspeito> findAllOrderByMomentoDoAlerta();

	Page<CarroSuspeito> findByPlaca(String placa,Pageable paginacao);
}
