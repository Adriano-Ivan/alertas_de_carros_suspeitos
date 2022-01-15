package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;

@MappedSuperclass
public abstract class Indicativo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	@OneToMany(mappedBy="nivelDeUrgencia",fetch=FetchType.LAZY)
	@JsonManagedReference
	protected List<VeiculoSuspeito> veiculosSuspeitos;
	
	@OneToMany(mappedBy="nivelDeUrgencia",fetch=FetchType.LAZY)
	@JsonManagedReference
	protected List<VeiculoRoubado> veiculosRoubados;
	
	@OneToMany(mappedBy="nivelDeUrgencia",fetch=FetchType.LAZY)
	@JsonManagedReference
	protected List<VeiculoEmSituacaoIrregular> veiculosEmSituacaoIrregular;
	
	@OneToMany(mappedBy="nivelDeUrgencia",fetch=FetchType.LAZY)
	@JsonManagedReference
	protected List<VeiculoComInfracao> veiculosComInfracao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<VeiculoSuspeito> getVeiculosSuspeitos() {
		return veiculosSuspeitos;
	}

	public List<VeiculoRoubado> getVeiculosRoubados() {
		return veiculosRoubados;
	}

	public List<VeiculoEmSituacaoIrregular> getVeiculosEmSituacaoIrregular() {
		return veiculosEmSituacaoIrregular;
	}

	public List<VeiculoComInfracao> getVeiculosComInfracao() {
		return veiculosComInfracao;
	}
}
