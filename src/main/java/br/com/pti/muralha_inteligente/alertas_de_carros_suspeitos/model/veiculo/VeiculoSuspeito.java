package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.VeiculoSuspeitoDto;

@Entity
@Table(name="veiculos_suspeitos")
public class VeiculoSuspeito extends Veiculo {
	
	@Column(columnDefinition = "TEXT")
	private String justificativa;
	
	public VeiculoSuspeito() {}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public static List<VeiculoSuspeitoDto> converter(List<VeiculoSuspeito> veiculosSuspeitos) {
		return veiculosSuspeitos.stream().map(VeiculoSuspeitoDto::new)
				.collect(Collectors.toList());
	}
}
