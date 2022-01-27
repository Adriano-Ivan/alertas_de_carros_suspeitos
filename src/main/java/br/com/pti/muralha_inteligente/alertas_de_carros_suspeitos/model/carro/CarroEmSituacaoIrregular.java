package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroEmSituacaoIrregularDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroRoubadoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroEmSituacaoIrregularForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.enumerator.MedidaAdministrativa;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@Entity
@Table(name="carros_em_situacao_irregular")
public class CarroEmSituacaoIrregular extends Carro implements RelacionavelParaJson {
	
	@Enumerated(EnumType.STRING)
	private MedidaAdministrativa medidaAdministrativa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="zone-irr-movement")
	protected Zona zona;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="inser-irr-movement")
	protected Usuario usuarioInsersor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="edited-irr-movement")
	protected Usuario ultimoUsuarioEditor;
	
	
	public CarroEmSituacaoIrregular() {}

	public CarroEmSituacaoIrregular(CarroEmSituacaoIrregularForm veiculoForm, Zona zona,
			Usuario usuarioInsersor) {
		super(veiculoForm,zona);
		this.medidaAdministrativa=veiculoForm.getMedidaAdministrativa();
		this.zona=zona;
		this.usuarioInsersor=usuarioInsersor;
		//this.ultimoUsuarioEditor=usuarioEditor;
	}

	public MedidaAdministrativa getMedidaAdministrativa() {
		return medidaAdministrativa;
	}

	public void setMedidaAdministrativa(MedidaAdministrativa medidaAdministrativa) {
		this.medidaAdministrativa = medidaAdministrativa;
	}
	@JsonBackReference(value="edited-irr-movement")
	public Usuario getUltimoUsuarioEditor() {
		return ultimoUsuarioEditor;
	}

	public void setUltimoUsuarioEditor(Usuario ultimoUsuarioEditor) {
		this.ultimoUsuarioEditor = ultimoUsuarioEditor;
	}
	@JsonBackReference(value="zone-irr-movement")
	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	@JsonBackReference(value="inser-irr-movement")
	public Usuario getUsuarioInsersor() {
		return usuarioInsersor;
	}

	public void setUsuarioInsersor(Usuario usuarioInsersor) {
		this.usuarioInsersor = usuarioInsersor;
	}
	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha", "medida administrativa: "+medidaAdministrativa);
	}

	public static Page<CarroEmSituacaoIrregularDto> converter(Page<CarroEmSituacaoIrregular> veiculosEmSituacaoIrregular) {
		return veiculosEmSituacaoIrregular.map(CarroEmSituacaoIrregularDto::new);
	}

	public static CarroEmSituacaoIrregularDto converter(CarroEmSituacaoIrregular veiculoEmSituacaoIrregular) {
		// TODO Auto-generated method stub
		return new CarroEmSituacaoIrregularDto(veiculoEmSituacaoIrregular);
	}

}
