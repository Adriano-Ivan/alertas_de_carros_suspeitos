package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoRoubadoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="veiculos_roubados")
public class VeiculoRoubado extends Veiculo{
	
	@Column(length=80)
	private String localDoRoubo;
	
	public VeiculoRoubado() {}

	public VeiculoRoubado(VeiculoRoubadoForm veiculoForm, Zona zona, Usuario usuarioEditor,
			Usuario usuarioInsersor) {
		super(veiculoForm,zona,usuarioEditor,usuarioInsersor);
		this.localDoRoubo=veiculoForm.getLocalDoRoubo();
	}

	public String getLocalDoRoubo() {
		return localDoRoubo;
	}

	public void setLocalDoRoubo(String localDoRoubo) {
		this.localDoRoubo = localDoRoubo;
	}

	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha", "local do roubo: "+localDoRoubo);
	}
	
	
}
