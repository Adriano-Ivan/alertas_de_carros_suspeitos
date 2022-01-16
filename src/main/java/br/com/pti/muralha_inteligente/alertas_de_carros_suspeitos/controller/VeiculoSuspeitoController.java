package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/veiculo_suspeito")
public class VeiculoSuspeitoController {

	@GetMapping
	public String veiculoSuspeito() {
		return "veiculo/veiculo_suspeito";
	}
}
