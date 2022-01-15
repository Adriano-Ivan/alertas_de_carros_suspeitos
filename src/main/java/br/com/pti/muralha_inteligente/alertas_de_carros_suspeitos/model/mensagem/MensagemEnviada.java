package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="mensagens_enviadas")
public class MensagemEnviada extends Mensagem{
	public MensagemEnviada() {}
}
