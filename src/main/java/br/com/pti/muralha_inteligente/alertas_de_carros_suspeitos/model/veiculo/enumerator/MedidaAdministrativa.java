package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.enumerator;

public enum MedidaAdministrativa {
	REMOCAO("Remoção"), RETENCAO("Retenção");
	
	private String descricao;
	
	MedidaAdministrativa(String descricao){
		this.descricao=descricao;
	}
	public String getDescricao() {
		return this.descricao;
	}
}
