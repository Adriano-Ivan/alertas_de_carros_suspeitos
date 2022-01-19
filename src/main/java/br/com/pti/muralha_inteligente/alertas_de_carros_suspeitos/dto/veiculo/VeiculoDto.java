package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.Veiculo;

public abstract class VeiculoDto {

	protected Long id;
	protected String dono;
	protected String placa;
	protected String localDoAlerta;
	protected LocalDateTime momentoDoAlerta;
	protected Boolean alertado;
	protected String nivelDeUrgencia;
	protected String statusDoVeiculo;
	protected Long idZona;
	protected Long idUsuarioInsersor;
	protected Long idUltimoUsuarioEditor;
		
	public VeiculoDto() {}
	public VeiculoDto(Veiculo veiculo) {
		this.id = veiculo.getId();
		this.dono = veiculo.getDono();
		this.placa = veiculo.getPlaca();
		this.localDoAlerta = veiculo.getLocalDoAlerta();
		this.momentoDoAlerta = veiculo.getMomentoDoAlerta();
		this.alertado = veiculo.getAlertado();
		
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

}
