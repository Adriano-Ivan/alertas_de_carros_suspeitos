package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;

public interface CarroRoubadoRepository extends
	JpaRepository<CarroRoubado, Long>{

	Page<CarroRoubado> findByPlaca(String placa, Pageable paginacao);

}
