package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.Carro;

public abstract class CarroDto {

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
	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
		
	public CarroDto() {}
	public CarroDto(Carro veiculo) {
		this.id = veiculo.getId();
		this.dono = veiculo.getDono();
		this.placa = veiculo.getPlaca();
		this.localDoAlerta = veiculo.getLocalDoAlerta();
		this.momentoDoAlerta = veiculo.getMomentoDoAlerta();
		this.alertado = veiculo.getAlertado();
		this.createdAt=veiculo.getCreatedAt();
		this.updatedAt=veiculo.getUpdatedAt();
		this.nivelDeUrgencia = veiculo.getNivelDeUrgencia();
		this.statusDoVeiculo = veiculo.getStatusDoVeiculo();
		
		if(veiculo.getCreatedAt()!=null) {
			createdAt=veiculo.getCreatedAt();
		}
		if(veiculo.getUpdatedAt()!=null) {
			updatedAt=veiculo.getUpdatedAt();
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

	public Long getIdZona() {
		return idZona;
	}

	public Long getIdUsuarioInsersor() {
		return idUsuarioInsersor;
	}

	public Long getIdUltimoUsuarioEditor() {
		return idUltimoUsuarioEditor;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
}
