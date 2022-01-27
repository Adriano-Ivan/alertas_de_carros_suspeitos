package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroRoubadoParaUsuario;

public interface AlertaDeCarroRoubadoParaUsuarioRepository extends
	JpaRepository<AlertaDeCarroRoubadoParaUsuario, Long>{

}
