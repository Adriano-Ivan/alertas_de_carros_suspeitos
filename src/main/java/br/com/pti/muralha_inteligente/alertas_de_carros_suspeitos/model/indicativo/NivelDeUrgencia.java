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
public class NivelDeUrgencia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private NivelUrgencia nivelDeUrgencia;

	@OneToMany(mappedBy="nivelDeUrgencia",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<VeiculoSuspeito> veiculosSuspeitos;
	
	@OneToMany(mappedBy="nivelDeUrgencia",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<VeiculoRoubado> veiculosRoubados;
	
	@OneToMany(mappedBy="nivelDeUrgencia",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<VeiculoEmSituacaoIrregular> veiculosEmSituacaoIrregular;
	
	@OneToMany(mappedBy="nivelDeUrgencia",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<VeiculoComInfracao> veiculosComInfracao;
	
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NivelUrgencia getNivelUrgencia() {
		return nivelDeUrgencia;
	}

	public void setNivelUrgencia(NivelUrgencia nivelUrgencia) {
		this.nivelDeUrgencia = nivelUrgencia;
	}
}
