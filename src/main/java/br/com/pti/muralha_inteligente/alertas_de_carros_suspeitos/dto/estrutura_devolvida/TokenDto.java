package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida;

public class TokenDto {

	private String tipo;
	private String token;
	
	public TokenDto(String token, String tipo) {
		this.tipo=tipo;
		this.token=token;
	}

	public String getTipo() {
		return tipo;
	}

	public String getToken() {
		return token;
	}
}
