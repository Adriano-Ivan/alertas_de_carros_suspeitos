package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.UsuarioDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.UsuarioForm;
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
public class Usuario implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=130,unique=true)
	private String nomeDeUsuario;
	
	@Column(length=130,unique=true)
	private String email;
	
	@Column(length=256)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private TipoAutoridade autoridade;
	
	private Long insercoes;
	
	@Column(length=256)
	private String token;

	@ManyToOne(fetch=FetchType.EAGER)
	private Zona zona;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario usuarioInsersor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Usuario ultimoUsuarioEditor;
	
	protected LocalDateTime createdAt;
	
	protected LocalDateTime updatedAt;

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();

	public Usuario() {}
	
	public Usuario(UsuarioForm usuarioForm, Usuario usuarioInsersor, Zona zona) {
		if(usuarioForm.getAutoridade()!=null) {
			autoridade=usuarioForm.getAutoridade();
		}
		if(usuarioForm.getToken()!=null) {
			token=usuarioForm.getToken();
		}
		
		createdAt=LocalDateTime.now();
		this.usuarioInsersor=usuarioInsersor;
		email=usuarioForm.getEmail();
		senha=usuarioForm.getSenha();
		nomeDeUsuario=usuarioForm.getNomeDeUsuario();
		this.zona=zona;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Perfil> getPerfis() {
		return perfis;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nomeDeUsuario=" + nomeDeUsuario + ", email=" + email + ", autoridade="
				+ autoridade + ", insercoes=" + insercoes + ", zona=" + zona + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis ;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.nomeDeUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {
		return usuarios.map(UsuarioDto::new);
	}

	public static UsuarioDto converter(Usuario usuario) {
		return new UsuarioDto(usuario);
	}
	
}
