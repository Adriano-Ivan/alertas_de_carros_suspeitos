package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo.NivelDeUrgencia;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo.StatusDoVeiculo;

@MappedSuperclass
public abstract class Veiculo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=100)
	private String dono;
	
	@Column(length=7)
	private String placa;
	
	@Column(length=80)
	private String localDoAlerta;
	
	private LocalDateTime momentoDoAlerta;
	
	private Boolean alertado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	private NivelDeUrgencia nivelDeUrgencia;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	private StatusDoVeiculo statusDoVeiculo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	private Zona zona;
}
