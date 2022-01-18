package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.VeiculoSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.VeiculoSuspeitoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoSuspeitoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculosRest {

	@Autowired
	private VeiculoSuspeitoRepository veiculoSuspeitoRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/suspeitos")
	public List<VeiculoSuspeitoDto> listar(){
		List<VeiculoSuspeito> veiculosSuspeitos = veiculoSuspeitoRepository.findAllOrderByMomentoDoAlerta();
		
		
		return VeiculoSuspeito.converter(veiculosSuspeitos);
	}
	
	
	@GetMapping("/suspeitos/{id}")
	public ResponseEntity<VeiculoSuspeitoDto> retornarEspecifico(@PathVariable("id") Long id) {
		Optional<VeiculoSuspeito> veiculo = veiculoSuspeitoRepository.findById(id);
		if(veiculo.isPresent()) {
			return ResponseEntity.ok(VeiculoSuspeito.converter(veiculo.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/suspeitos")
	@Transactional
	public ResponseEntity<VeiculoSuspeitoDto> cadastar(@RequestBody @Valid VeiculoSuspeitoForm form,
			UriComponentsBuilder uriBuilder ){
		VeiculoSuspeito veiculo = form.converter(zonaRepository,usuarioRepository);
		veiculoSuspeitoRepository.save(veiculo);
		
		URI uri = uriBuilder.path("/api/veiculos/suspeitos/{id}")
				.buildAndExpand(veiculo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VeiculoSuspeitoDto(veiculo));
	}

	
	@PutMapping("/suspeitos/{id}")
	@Transactional
	public ResponseEntity<VeiculoSuspeitoDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid VeiculoSuspeitoForm form){
		Optional<VeiculoSuspeito> veiculoOpt = veiculoSuspeitoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			VeiculoSuspeito veiculo = form.atualizar(id,veiculoSuspeitoRepository,
					zonaRepository, usuarioRepository);
			return ResponseEntity.ok(VeiculoSuspeito.converter(veiculo));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/suspeitos/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<VeiculoSuspeito> veiculoOpt=veiculoSuspeitoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			veiculoSuspeitoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
