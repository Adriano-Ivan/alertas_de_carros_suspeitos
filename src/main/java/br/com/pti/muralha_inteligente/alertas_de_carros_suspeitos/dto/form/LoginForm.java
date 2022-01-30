package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String emailOuNomeDeUsuario;
	private String senha;
	
	public String getEmailOuNomeDeUsuario() {
		return emailOuNomeDeUsuario;
	}
	
	public void setEmailOuNomeDeUsuario(String emailOuNomeDeUsuario) {
		this.emailOuNomeDeUsuario = emailOuNomeDeUsuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(emailOuNomeDeUsuario,senha);
	}
}
