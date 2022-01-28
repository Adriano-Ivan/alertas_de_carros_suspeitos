package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario;

import java.time.LocalDateTime;
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
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.enumerator.TipoAutoridade;

@Entity
@Table(name="usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=130,unique=true)
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
	
	protected LocalDateTime createdAt;
	
	protected LocalDateTime updatedAt;
	
	@OneToMany(mappedBy="usuario",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonManagedReference(value="bot-user-user-movement")
	private List<BotDoTelegramDeUsuario> botsDoTelegramDeUsuario;
	
	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="local-user-movement")
	private List<LocalAlvo> locaisAlvoInseridos;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="local-user-edited-movement")
	private List<LocalAlvo> locaisAlvoEditados;
	
	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="zone-user-movement")
	private List<Zona> zonasInseridas;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="zone-user-edited-movement")
	private List<Zona> zonasEditadas;
	
	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="inser-infr-movement")
	private List<CarroComInfracao> carrosComInfracaoInseridos;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="edited-infr-movement")
	private List<CarroComInfracao> carrosComInfracaoEditados;
	
	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="inser-irr-movement")
	private List<CarroEmSituacaoIrregular> carrosEmSituacoaIrregularInseridos;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="edited-irr-movement")
	private List<CarroEmSituacaoIrregular> carrosEmSituacoaIrregularEditados;

	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="inser-stolen-movement")
	private List<CarroRoubado> carrosRoubadosInseridos;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="edited-stolen-movement")
	private List<CarroRoubado> carrosRoubadosEditados;
	
	@OneToMany(mappedBy="usuarioInsersor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="inser-suspects-movement")
	private List<CarroSuspeito> carrosSuspeitosInseridos;
	
	@OneToMany(mappedBy="ultimoUsuarioEditor",fetch=FetchType.LAZY)
	@JsonManagedReference(value="edited-suspects-movement")
	private List<CarroSuspeito> carrosSuspeitosEditados;

	
	public List<CarroComInfracao> getCarrosComInfracaoInseridos() {
		return carrosComInfracaoInseridos;
	}

	public List<CarroComInfracao> getCarrosComInfracaoEditados() {
		return carrosComInfracaoEditados;
	}

	public List<CarroEmSituacaoIrregular> getCarrosEmSituacoaIrregularInseridos() {
		return carrosEmSituacoaIrregularInseridos;
	}

	public List<CarroEmSituacaoIrregular> getCarrosEmSituacoaIrregularEditados() {
		return carrosEmSituacoaIrregularEditados;
	}

	public List<BotDoTelegramDeUsuario> getBotsDoTelegramDeUsuario() {
		return botsDoTelegramDeUsuario;
	}

	public List<CarroRoubado> getCarrosRoubadosInseridos() {
		return carrosRoubadosInseridos;
	}

	public List<CarroRoubado> getCarrosRoubadosEditados() {
		return carrosRoubadosEditados;
	}
	@JsonManagedReference(value="inser-suspects-movement")
	public List<CarroSuspeito> getCarrosSuspeitosInseridos() {
		return carrosSuspeitosInseridos;
	}
	@JsonManagedReference(value="edited-suspects-movement")
	public List<CarroSuspeito> getCarrosSuspeitosEditados() {
		return carrosSuspeitosEditados;
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
