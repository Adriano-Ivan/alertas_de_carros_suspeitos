package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.indicativo.enumerator;

public enum StatusVeiculo {
	ATIVO("Em andamento"), RESOLVIDO("Resolvido"), A_CONFIRMAR("A CONFIRMAR");

	private String descricao;
	
	StatusVeiculo(String descricao) {
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
