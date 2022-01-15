package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;

@Entity
@Table(name="zonas")
public class Zona {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=40,nullable=false)
	private String zona;

	@OneToMany(mappedBy="zona",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<Usuario> usuarios;
	
	@OneToMany(mappedBy="zonaAssociada",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<LocalAlvo> locaisAlvo;
	
	@OneToMany(mappedBy="zona",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<VeiculoSuspeito> veiculosSuspeitos;
	
	@OneToMany(mappedBy="zona",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<VeiculoRoubado> veiculosRoubados;
	
	@OneToMany(mappedBy="zona",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<VeiculoEmSituacaoIrregular> veiculosEmSituacaoIrregular;
	
	@OneToMany(mappedBy="zona",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<VeiculoComInfracao> veiculosComInfracao;
	
	@OneToMany(mappedBy="zona",fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<BotDoTelegram> botsDoTelegram;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	private Usuario usuarioInsersor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	private Usuario ultimoUsuarioEditor;
	
	public List<BotDoTelegram> getBotsDoTelegram() {
		return botsDoTelegram;
	}
	public List<VeiculoSuspeito> getVeiculosSuspeitos() {
		return veiculosSuspeitos;
	}
	public List<VeiculoRoubado> getVeiculosRoubados() {
		return veiculosRoubados;
	}
	public List<VeiculoEmSituacaoIrregular> getVeiculosEmSituacaoIrregular() {
		return veiculosEmSituacaoIrregular;
	}
	public List<VeiculoComInfracao> getVeiculosComInfracao() {
		return veiculosComInfracao;
	}
	public List<LocalAlvo> getLocaisAlvo() {
		return locaisAlvo;
	}
	public void adicionarLocalAlvo(LocalAlvo localAlvo) {
		this.locaisAlvo.add(localAlvo);
	}
	public Usuario getUsuarioInsersor() {
		return usuarioInsersor;
	}

	public void setUsuarioInsersor(Usuario usuarioInsersor) {
		this.usuarioInsersor = usuarioInsersor;
	}

	public Usuario getUltimoUsuarioEditor() {
		return ultimoUsuarioEditor;
	}

	public void setUltimoUsuarioEditor(Usuario ultimoUsuarioEditor) {
		this.ultimoUsuarioEditor = ultimoUsuarioEditor;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void adicionarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}
}
