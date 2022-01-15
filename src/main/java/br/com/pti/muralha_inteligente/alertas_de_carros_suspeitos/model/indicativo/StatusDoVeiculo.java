package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo.enumerator.StatusVeiculo;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;

@Entity
@Table(name="status_do_veiculo")
public class StatusDoVeiculo extends Indicativo {
	
	@Enumerated(EnumType.STRING)
	private StatusVeiculo statusDoVeiculo;

	public StatusDoVeiculo() {}
	
	public StatusVeiculo getStatusVeiculo() {
		return statusDoVeiculo;
	}

	public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
		this.statusDoVeiculo = statusVeiculo;
	}
	
	
}
