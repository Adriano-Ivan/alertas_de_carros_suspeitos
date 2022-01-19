package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoComInfracaoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator.GravidadeDaInfracao;

@Entity
@Table(name="veiculos_com_infracao")
public class VeiculoComInfracao extends Veiculo{

	@Enumerated(EnumType.STRING)
	private GravidadeDaInfracao gravidadeDaInfracao;
	public VeiculoComInfracao() {}
	
	public VeiculoComInfracao(VeiculoComInfracaoForm veiculoForm, Zona zona, Usuario usuarioEditor,
			Usuario usuarioInsersor) {
		super(veiculoForm,zona,usuarioEditor,usuarioInsersor);
	}

	public GravidadeDaInfracao getGravidadeDaInfracao() {
		return this.gravidadeDaInfracao;
	}
	public void setGravidadeDaInfracao(GravidadeDaInfracao gdi) {
		this.gravidadeDaInfracao=gdi;
	}

	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha","gravidade da infração: "+gravidadeDaInfracao);
	}
	
}
