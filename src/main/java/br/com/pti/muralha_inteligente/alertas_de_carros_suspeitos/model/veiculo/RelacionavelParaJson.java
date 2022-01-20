package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;

public interface RelacionavelParaJson {
	public Usuario getUltimoUsuarioEditor();
	public void setUltimoUsuarioEditor(Usuario ultimoUsuarioEditor);
	public Zona getZona() ;
	public void setZona(Zona zona);
	public Usuario getUsuarioInsersor();
	public void setUsuarioInsersor(Usuario usuarioInsersor) ;
}
