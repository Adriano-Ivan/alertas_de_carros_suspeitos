package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo.NivelDeUrgencia;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo.StatusDoVeiculo;
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
	private String zona;
	private String usuarioInsersor;
	private String ultimoUsuarioEditor;
	private String justificativa;
	
	public VeiculoSuspeitoDto(VeiculoSuspeito veiculo) {
		this.id=veiculo.getId();
		this.dono=veiculo.getDono();
		this.placa=veiculo.getPlaca();
		this.localDoAlerta=veiculo.getLocalDoAlerta();
		this.momentoDoAlerta=veiculo.getMomentoDoAlerta();
		this.alertado=veiculo.getAlertado();
		this.justificativa=veiculo.getJustificativa();
		
		if(veiculo.getNivelDeUrgencia()!=null) {
			this.nivelDeUrgencia=veiculo.getNivelDeUrgencia().getNivelUrgencia().getDescricao();
		}
		if(veiculo.getStatusDoVeiculo()!=null) {
			this.statusDoVeiculo=veiculo.getStatusDoVeiculo().getStatusVeiculo().getDescricao();
		}
		
		if(veiculo.getZona() !=null) {
			this.zona=veiculo.getZona().getZona();
		}
		if(veiculo.getUsuarioInsersor() !=null) {
			this.usuarioInsersor=veiculo.getUsuarioInsersor().getNomeDeUsuario();
		}
		if(veiculo.getUltimoUsuarioEditor() !=null) {
			this.ultimoUsuarioEditor=veiculo.getUltimoUsuarioEditor().getNomeDeUsuario();
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

	public String getZona() {
		return zona;
	}

	public String getUsuarioInsersor() {
		return usuarioInsersor;
	}

	public String getUltimoUsuarioEditor() {
		return ultimoUsuarioEditor;
	}

	public String getJustificativa() {
		return justificativa;
	}
	
}
