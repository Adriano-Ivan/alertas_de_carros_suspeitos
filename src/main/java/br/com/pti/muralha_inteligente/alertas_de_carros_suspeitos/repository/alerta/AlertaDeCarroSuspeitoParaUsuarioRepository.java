package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroSuspeitoParaUsuario;

public interface AlertaDeCarroSuspeitoParaUsuarioRepository extends
	JpaRepository<AlertaDeCarroSuspeitoParaUsuario, Long>{

}