package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.VeiculoSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoSuspeitoRepository;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculosRest {

	@Autowired
	private VeiculoSuspeitoRepository veiculoSuspeitoRepository;
	
	@GetMapping("/suspeitos")
	public List<VeiculoSuspeitoDto> getVeiculosSuspeitos(){
		List<VeiculoSuspeito> veiculosSuspeitos = veiculoSuspeitoRepository.findAll();
		
		return VeiculoSuspeito.converter(veiculosSuspeitos);
	}
}
