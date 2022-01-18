package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.config.validacao;

public class ErroDeFormularioDto {

	private String campo;
	private String mensagem;
	
	public ErroDeFormularioDto(String campo, String mensagem) {
		super();
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}
}
