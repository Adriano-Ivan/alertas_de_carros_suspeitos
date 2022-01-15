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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo.enumerator.NivelUrgencia;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;

@Entity
@Table(name="niveis_de_urgencia")
public class NivelDeUrgencia extends Indicativo {

	@Enumerated(EnumType.STRING)
	private NivelUrgencia nivelDeUrgencia;

	public NivelDeUrgencia() {}
	
	public NivelUrgencia getNivelUrgencia() {
		return nivelDeUrgencia;
	}

	public void setNivelUrgencia(NivelUrgencia nivelUrgencia) {
		this.nivelDeUrgencia = nivelUrgencia;
	}
}
