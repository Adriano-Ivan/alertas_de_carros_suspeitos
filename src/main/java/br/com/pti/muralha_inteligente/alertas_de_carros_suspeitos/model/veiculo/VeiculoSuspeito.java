package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoSuspeitoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="veiculos_suspeitos")
public class VeiculoSuspeito extends Veiculo {
	
	@Column(columnDefinition = "TEXT")
	private String justificativa;
	
	public VeiculoSuspeito() {}

	public VeiculoSuspeito(VeiculoSuspeitoForm veiculoForm, Zona zona, Usuario usuarioEditor,
			Usuario usuarioInsersor) {
		super(veiculoForm, zona,  usuarioEditor,
			usuarioInsersor);
		this.justificativa=veiculoForm.getJustificativa();

	}

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

	public static VeiculoSuspeitoDto converter(VeiculoSuspeito veiculo) {
		return new VeiculoSuspeitoDto(veiculo);
	}
	
	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha", "justificativa: "+justificativa);
	}

	
}
