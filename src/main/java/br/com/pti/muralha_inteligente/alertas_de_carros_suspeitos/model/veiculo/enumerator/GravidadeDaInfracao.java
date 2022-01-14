package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator;

public enum GravidadeDaInfracao {
	LEVE("Leve"), MEDIA("Média"), GRAVE("Grave"), GRAVISSSIMA("Gravíssima");

	private String descricao;
	
	GravidadeDaInfracao(String descricao) {
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
