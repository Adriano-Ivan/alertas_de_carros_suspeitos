package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta;

import java.time.LocalDateTime;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

public abstract class AlertaDto {

	private Long id;
	
	private String latitude;
	
	private String longitude;
	
	private Boolean alertaEmitido;
	
	private String nomeDeUsuario;
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime createdAt;

	public AlertaDto(AlertaParaUsuario alerta) {
		this.id=alerta.getId();
		this.latitude=alerta.getLatitude();
		this.longitude=alerta.getLongitude();
		this.alertaEmitido=alerta.getAlertaEmitido();
		this.nomeDeUsuario=alerta.getUsuario().getNomeDeUsuario();
	
		if(alerta.getCreatedAt() !=null) {
			this.createdAt=alerta.getCreatedAt();
		}
		if(alerta.getUpdatedAt()!=null) {
			this.updatedAt=alerta.getUpdatedAt();
		}
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public Long getId() {
		return id;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public Boolean getAlertaEmitido() {
		return alertaEmitido;
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}
	
}
