package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroRoubadoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroRoubadoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

@Entity
@Table(name="carros_roubados")
public class CarroRoubado extends Carro implements RelacionavelParaJson{
	
	@Column(length=80)
	private String localDoRoubo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="zone-stolen-movement")
	protected Zona zona;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="inser-stolen-movement")
	protected Usuario usuarioInsersor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference(value="edited-stolen-movement")
	protected Usuario ultimoUsuarioEditor;
	
	public CarroRoubado() {}

	public CarroRoubado(CarroRoubadoForm veiculoForm, Zona zona,
			Usuario usuarioInsersor) {
		super(veiculoForm,zona);
		this.localDoRoubo=veiculoForm.getLocalDoRoubo();
		this.zona=zona;
		this.usuarioInsersor=usuarioInsersor;
		//this.ultimoUsuarioEditor=usuarioEditor;
	}

	public String getLocalDoRoubo() {
		return localDoRoubo;
	}

	public void setLocalDoRoubo(String localDoRoubo) {
		this.localDoRoubo = localDoRoubo;
	}
	@JsonBackReference(value="edited-stolen-movement")
	public Usuario getUltimoUsuarioEditor() {
		return ultimoUsuarioEditor;
	}

	public void setUltimoUsuarioEditor(Usuario ultimoUsuarioEditor) {
		this.ultimoUsuarioEditor = ultimoUsuarioEditor;
	}
	@JsonBackReference(value="zone-stolen-movement")
	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	@JsonBackReference(value="inser-stolen-movement")
	public Usuario getUsuarioInsersor() {
		return usuarioInsersor;
	}

	public void setUsuarioInsersor(Usuario usuarioInsersor) {
		this.usuarioInsersor = usuarioInsersor;
	}

	public static Page<CarroRoubadoDto> converter(Page<CarroRoubado> veiculosSuspeitos) {
		return veiculosSuspeitos.map(CarroRoubadoDto::new);
	}
	@Override
	public String toString() {
		return super.toString().replace("próprio_da_filha", "local do roubo: "+localDoRoubo)
				.replace("insersor", usuarioInsersor.toString()).replace("editor",ultimoUsuarioEditor.toString())
				.replace("zona", zona.toString());
	}

	public static CarroRoubadoDto converter(CarroRoubado veiculoRoubado) {
		// TODO Auto-generated method stub
		return new CarroRoubadoDto(veiculoRoubado);
	}
	
	
}
