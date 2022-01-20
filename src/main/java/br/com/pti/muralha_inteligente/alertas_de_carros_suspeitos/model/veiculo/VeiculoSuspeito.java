package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoSuspeitoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="veiculos_suspeitos")
public class VeiculoSuspeito extends Veiculo implements RelacionavelParaJson {
	
	@Column(columnDefinition = "TEXT")
	private String justificativa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="zone-suspects-movement")
	protected Zona zona;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="inser-suspects-movement")
	protected Usuario usuarioInsersor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="edited-suspects-movement")
	protected Usuario ultimoUsuarioEditor;
	
	public VeiculoSuspeito() {}

	public VeiculoSuspeito(VeiculoSuspeitoForm veiculoForm, Zona zona, Usuario usuarioEditor,
			Usuario usuarioInsersor) {
		super(veiculoForm, zona,  usuarioEditor,
			usuarioInsersor);
		this.justificativa=veiculoForm.getJustificativa();
		this.zona=zona;
		this.usuarioInsersor=usuarioInsersor;
		this.ultimoUsuarioEditor=usuarioEditor;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	@JsonBackReference(value="edited-suspects-movement")
	public Usuario getUltimoUsuarioEditor() {
		return ultimoUsuarioEditor;
	}

	public void setUltimoUsuarioEditor(Usuario ultimoUsuarioEditor) {
		this.ultimoUsuarioEditor = ultimoUsuarioEditor;
	}
	@JsonBackReference(value="zone-suspects-movement")
	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	@JsonBackReference(value="inser-suspects-movement")
	public Usuario getUsuarioInsersor() {
		return usuarioInsersor;
	}

	public void setUsuarioInsersor(Usuario usuarioInsersor) {
		this.usuarioInsersor = usuarioInsersor;
	}

	public static Page<VeiculoSuspeitoDto> converter(Page<VeiculoSuspeito> veiculosSuspeitos) {
//		return veiculosSuspeitos.stream().map(VeiculoSuspeitoDto::new)
//				.collect(Collectors.toList());
		return veiculosSuspeitos.map(VeiculoSuspeitoDto::new);
	}

	public static VeiculoSuspeitoDto converter(VeiculoSuspeito veiculo) {
		return new VeiculoSuspeitoDto(veiculo);
	}
	
	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha", "justificativa: "+justificativa)
				.replace("insersor", usuarioInsersor.toString()).replace("editor",ultimoUsuarioEditor.toString())
				.replace("zona", zona.toString());
	}

	
}
