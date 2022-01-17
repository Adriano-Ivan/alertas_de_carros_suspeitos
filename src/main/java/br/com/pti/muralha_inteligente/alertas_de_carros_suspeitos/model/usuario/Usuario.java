package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.BotDoTelegramDeUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.LocalAlvo;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.enumerator.TipoAutoridade;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;

@Entity
@Table(name="usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=130)
	private String nomeDeUsuario;
	
	@Column(length=130)
	private String email;
	
	@Column(length=256)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private TipoAutoridade autoridade;
	
	private Long insercoes;
	
	@Column(length=256)
	private String token;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Zona zona;
	
	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<LocalAlvo> locaisAlvoInseridos;
	
	@OneToMany(mappedBy="usuario",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonIgnore
	private List<BotDoTelegramDeUsuario> botsDoTelegramDeUsuario;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<LocalAlvo> locaisAlvoEditados;
	
	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Zona> zonasInseridas;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Zona> zonasEditadas;
	
	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<VeiculoComInfracao> veiculosComInfracaoInseridos;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<VeiculoComInfracao> veiculosComInfracaoEditados;
	
	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<VeiculoEmSituacaoIrregular> veiculosEmSituacoaIrregularInseridos;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<VeiculoEmSituacaoIrregular> veiculosEmSituacoaIrregularEditados;

	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<VeiculoRoubado> veiculosRoubadosInseridos;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<VeiculoRoubado> veiculosRoubadosEditados;
	
	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="inser-suspects-movement")
	private List<VeiculoSuspeito> veiculosSuspeitosInseridos;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="edited-suspects-movement")
	private List<VeiculoSuspeito> veiculosSuspeitosEditados;

	
	public List<VeiculoComInfracao> getVeiculosComInfracaoInseridos() {
		return veiculosComInfracaoInseridos;
	}

	public List<VeiculoComInfracao> getVeiculosComInfracaoEditados() {
		return veiculosComInfracaoEditados;
	}

	public List<VeiculoEmSituacaoIrregular> getVeiculosEmSituacoaIrregularInseridos() {
		return veiculosEmSituacoaIrregularInseridos;
	}

	public List<VeiculoEmSituacaoIrregular> getVeiculosEmSituacoaIrregularEditados() {
		return veiculosEmSituacoaIrregularEditados;
	}

	public List<BotDoTelegramDeUsuario> getBotsDoTelegramDeUsuario() {
		return botsDoTelegramDeUsuario;
	}

	public List<VeiculoRoubado> getVeiculosRoubadosInseridos() {
		return veiculosRoubadosInseridos;
	}

	public List<VeiculoRoubado> getVeiculosRoubadosEditados() {
		return veiculosRoubadosEditados;
	}
	@JsonManagedReference(value="inser-suspects-movement")
	public List<VeiculoSuspeito> getVeiculosSuspeitosInseridos() {
		return veiculosSuspeitosInseridos;
	}
	@JsonManagedReference(value="edited-suspects-movement")
	public List<VeiculoSuspeito> getVeiculosSuspeitosEditados() {
		return veiculosSuspeitosEditados;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoAutoridade getAutoridade() {
		return autoridade;
	}

	public void setAutoridade(TipoAutoridade autoridade) {
		this.autoridade = autoridade;
	}

	public Long getInsercoes() {
		return insercoes;
	}

	public void setInsercoes(Long insercoes) {
		this.insercoes = insercoes;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public List<LocalAlvo> getLocaisAlvoInseridos() {
		return locaisAlvoInseridos;
	}

	public List<LocalAlvo> getLocaisAlvoEditados() {
		return locaisAlvoEditados;
	}

	public List<Zona> getZonasInseridas() {
		return zonasInseridas;
	}

	public List<Zona> getZonasEditadas() {
		return zonasEditadas;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nomeDeUsuario=" + nomeDeUsuario + ", email=" + email + ", autoridade="
				+ autoridade + ", insercoes=" + insercoes + ", zona=" + zona + "]";
	}
	
}
