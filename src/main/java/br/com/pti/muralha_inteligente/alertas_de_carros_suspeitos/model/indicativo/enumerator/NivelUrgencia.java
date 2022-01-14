package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo.enumerator;

public enum NivelUrgencia {
	ROTINEIRO("Padrão"), MEDIANO("Mediano"), MUITO_ALTO("Urgentíssimo");

	private String descricao;
	
	NivelUrgencia(String descricao) {
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
