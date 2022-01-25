package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api.veiculo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoSuspeitoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoSuspeitoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@RestController
@RequestMapping("/api/v1/veiculos_suspeitos")
public class VeiculosSuspeitosRest {

	@Autowired
	private VeiculoSuspeitoRepository veiculoSuspeitoRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	@Cacheable(value="veículosSuspeitos")
	public Page<VeiculoSuspeitoDto> listar(@RequestParam(required=false) String placa,
			@PageableDefault(sort="momentoDoAlerta",direction=Direction.DESC,
			page=0,size=10) Pageable paginacao){
		//Pageable paginacao = PageRequest.of(pagina, qtde);
		if(placa==null) {
			Page<VeiculoSuspeito> veiculosSuspeitos = veiculoSuspeitoRepository.findAll(paginacao);
			
			return VeiculoSuspeito.converter(veiculosSuspeitos);
		}else {
			Page<VeiculoSuspeito> veiculosSuspeitos = veiculoSuspeitoRepository.findByPlaca(placa,
					paginacao);
			
			return VeiculoSuspeito.converter(veiculosSuspeitos);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<VeiculoSuspeitoDto> retornarEspecifico(@PathVariable("id") Long id) {
		Optional<VeiculoSuspeito> veiculo = veiculoSuspeitoRepository.findById(id);
		if(veiculo.isPresent()) {
			return ResponseEntity.ok(VeiculoSuspeito.converter(veiculo.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="veículosSuspeitos",
	allEntries=true)
	public ResponseEntity<VeiculoSuspeitoDto> cadastrar(@RequestBody @Valid VeiculoSuspeitoForm form,
			UriComponentsBuilder uriBuilder ){
		VeiculoSuspeito veiculo = form.converter(zonaRepository,usuarioRepository);
		veiculoSuspeitoRepository.save(veiculo);
		
		URI uri = uriBuilder.path("/api/v1/veiculos_suspeitos/{id}")
				.buildAndExpand(veiculo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VeiculoSuspeitoDto(veiculo));
	}

	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="veículosSuspeitos",
	allEntries=true)
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
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="veículosSuspeitos",
	allEntries=true)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<VeiculoSuspeito> veiculoOpt=veiculoSuspeitoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			veiculoSuspeitoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
