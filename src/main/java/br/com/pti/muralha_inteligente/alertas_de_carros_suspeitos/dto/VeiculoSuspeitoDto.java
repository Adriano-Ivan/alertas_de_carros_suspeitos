package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;

public class VeiculoSuspeitoDto {

	private Long id;
	private String dono;
	private String placa;
	private String localDoAlerta;
	private LocalDateTime momentoDoAlerta;
	private Boolean alertado;
	private String nivelDeUrgencia;
	private String statusDoVeiculo;
	private Long idZona;
	private Long idUsuarioInsersor;
	private Long idUltimoUsuarioEditor;
	private String justificativa;

	public VeiculoSuspeitoDto(VeiculoSuspeito veiculo) {
		this.id = veiculo.getId();
		this.dono = veiculo.getDono();
		this.placa = veiculo.getPlaca();
		this.localDoAlerta = veiculo.getLocalDoAlerta();
		this.momentoDoAlerta = veiculo.getMomentoDoAlerta();
		this.alertado = veiculo.getAlertado();
		this.justificativa = veiculo.getJustificativa();
		this.nivelDeUrgencia = veiculo.getNivelDeUrgencia();
		this.statusDoVeiculo = veiculo.getStatusDoVeiculo();

		if (veiculo.getZona() != null) {
			this.idZona = veiculo.getZona().getId();
		}
		if (veiculo.getUsuarioInsersor() != null) {
			this.idUsuarioInsersor = veiculo.getUsuarioInsersor().getId();
		}
		if (veiculo.getUltimoUsuarioEditor() != null) {
			this.idUltimoUsuarioEditor = veiculo.getUltimoUsuarioEditor().getId();
		}
	}

	public Long getId() {
		return id;
	}

	public String getDono() {
		return dono;
	}

	public String getPlaca() {
		return placa;
	}

	public String getLocalDoAlerta() {
		return localDoAlerta;
	}

	public LocalDateTime getMomentoDoAlerta() {
		return momentoDoAlerta;
	}

	public Boolean getAlertado() {
		return alertado;
	}

	public String getNivelDeUrgencia() {
		return nivelDeUrgencia;
	}

	public String getStatusDoVeiculo() {
		return statusDoVeiculo;
	}

	public Long getZona() {
		return idZona;
	}

	public Long getUsuarioInsersor() {
		return idUsuarioInsersor;
	}

	public Long getUltimoUsuarioEditor() {
		return idUltimoUsuarioEditor;
	}

	public String getJustificativa() {
		return justificativa;
	}

}
