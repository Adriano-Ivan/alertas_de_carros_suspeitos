package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;

public interface VeiculoRoubadoRepository extends
	JpaRepository<VeiculoRoubado, Long>{

}
