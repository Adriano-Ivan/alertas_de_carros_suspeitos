package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="mensagens_recebidas")
public class MensagemRecebida extends Mensagem {

	public MensagemRecebida() {}
}
