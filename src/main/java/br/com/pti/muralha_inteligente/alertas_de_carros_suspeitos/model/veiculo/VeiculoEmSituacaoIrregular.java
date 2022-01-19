package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoEmSituacaoIrregularForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoEmSituacaoIrregularDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoRoubadoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator.MedidaAdministrativa;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@Entity
@Table(name="veiculos_em_situacao_irregular")
public class VeiculoEmSituacaoIrregular extends Veiculo {
	
	@Enumerated(EnumType.STRING)
	private MedidaAdministrativa medidaAdministrativa;
	
	public VeiculoEmSituacaoIrregular() {}

	public VeiculoEmSituacaoIrregular(VeiculoEmSituacaoIrregularForm veiculoForm, Zona zona,
			Usuario usuarioEditor, Usuario usuarioInsersor) {
		super(veiculoForm,zona,usuarioEditor,usuarioInsersor);
		this.medidaAdministrativa=veiculoForm.getMedidaAdministrativa();
	}

	public MedidaAdministrativa getMedidaAdministrativa() {
		return medidaAdministrativa;
	}

	public void setMedidaAdministrativa(MedidaAdministrativa medidaAdministrativa) {
		this.medidaAdministrativa = medidaAdministrativa;
	}

	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha", "medida administrativa: "+medidaAdministrativa);
	}

	public static Page<VeiculoEmSituacaoIrregularDto> converter(Page<VeiculoEmSituacaoIrregular> veiculosEmSituacaoIrregular) {
		return veiculosEmSituacaoIrregular.map(VeiculoEmSituacaoIrregularDto::new);
	}

	public static VeiculoEmSituacaoIrregularDto converter(VeiculoEmSituacaoIrregular veiculoEmSituacaoIrregular) {
		// TODO Auto-generated method stub
		return new VeiculoEmSituacaoIrregularDto(veiculoEmSituacaoIrregular);
	}

}
