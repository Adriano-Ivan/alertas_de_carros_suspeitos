package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoComInfracaoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoComInfracaoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator.GravidadeDaInfracao;

@Entity
@Table(name="veiculos_com_infracao")
public class VeiculoComInfracao extends Veiculo implements RelacionavelParaJson{

	@Enumerated(EnumType.STRING)
	private GravidadeDaInfracao gravidadeDaInfracao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="zone-infr-movement")
	protected Zona zona;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="inser-infr-movement")
	protected Usuario usuarioInsersor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="edited-infr-movement")
	protected Usuario ultimoUsuarioEditor;
	
	
	public VeiculoComInfracao() {}
	
	public VeiculoComInfracao(VeiculoComInfracaoForm veiculoForm, Zona zona,
			Usuario usuarioInsersor) {
		super(veiculoForm,zona);
		this.gravidadeDaInfracao=veiculoForm.getGravidadeDaInfracao();
		this.zona=zona;
		this.usuarioInsersor=usuarioInsersor;
		//this.ultimoUsuarioEditor=usuarioEditor;
	}

	public GravidadeDaInfracao getGravidadeDaInfracao() {
		return this.gravidadeDaInfracao;
	}
	public void setGravidadeDaInfracao(GravidadeDaInfracao gdi) {
		this.gravidadeDaInfracao=gdi;
	}
	@JsonBackReference(value="edited-infr-movement")
	public Usuario getUltimoUsuarioEditor() {
		return ultimoUsuarioEditor;
	}

	public void setUltimoUsuarioEditor(Usuario ultimoUsuarioEditor) {
		this.ultimoUsuarioEditor = ultimoUsuarioEditor;
	}
	@JsonBackReference(value="zone-infr-movement")
	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	@JsonBackReference(value="inser-infr-movement")
	public Usuario getUsuarioInsersor() {
		return usuarioInsersor;
	}

	public void setUsuarioInsersor(Usuario usuarioInsersor) {
		this.usuarioInsersor = usuarioInsersor;
	}
	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha","gravidade da infração: "+gravidadeDaInfracao);
	}

	public static VeiculoComInfracaoDto converter(VeiculoComInfracao veiculo) {
		return new VeiculoComInfracaoDto(veiculo);
	}

	public static Page<VeiculoComInfracaoDto> converter(Page<VeiculoComInfracao> veiculosSuspeitos) {
		return veiculosSuspeitos.map(VeiculoComInfracaoDto::new);
	}
}
