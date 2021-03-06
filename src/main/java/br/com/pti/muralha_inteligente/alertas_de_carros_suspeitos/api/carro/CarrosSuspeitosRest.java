package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api.carro;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroSuspeitoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroSuspeitoRepository;

@RestController
@RequestMapping("${alertas_de_carros_suspeitos.api.base_servico}/carros_suspeitos")
public class CarrosSuspeitosRest {

	@Autowired
	private CarroSuspeitoRepository carroSuspeitoRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Value("${alertas_de_carros_suspeitos.api.base_servico}")
	private String base_da_url_do_servico;
	
	@GetMapping
	public Page<CarroSuspeitoDto> listar(@RequestParam(required=false) String placa,
			@PageableDefault(sort="momentoDoAlerta",direction=Direction.DESC,
			page=0,size=10) Pageable paginacao){
		//Pageable paginacao = PageRequest.of(pagina, qtde);
		if(placa==null) {
			Page<CarroSuspeito> veiculosSuspeitos = carroSuspeitoRepository.findAll(paginacao);
			
			return CarroSuspeito.converter(veiculosSuspeitos);
		}else {
			Page<CarroSuspeito> veiculosSuspeitos = carroSuspeitoRepository.findByPlaca(placa,
					paginacao);
			
			return CarroSuspeito.converter(veiculosSuspeitos);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroSuspeitoDto> retornarEspecifico(@PathVariable("id") Long id) {
		Optional<CarroSuspeito> veiculo = carroSuspeitoRepository.findById(id);
		if(veiculo.isPresent()) {
			return ResponseEntity.ok(CarroSuspeito.converter(veiculo.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CarroSuspeitoDto> cadastrar(@RequestBody @Valid CarroSuspeitoForm form,
			UriComponentsBuilder uriBuilder ){
		if(!form.validarUsuarioInsersorEzona(usuarioRepository, zonaRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		CarroSuspeito veiculo = form.converter(zonaRepository,usuarioRepository);
		carroSuspeitoRepository.save(veiculo);
		
		URI uri = uriBuilder.path(base_da_url_do_servico+"/veiculos_suspeitos/{id}")
				.buildAndExpand(veiculo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CarroSuspeitoDto(veiculo));
	}

	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CarroSuspeitoDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid CarroSuspeitoForm form){
		if(!form.validarUsuarioEditorEzona(usuarioRepository, zonaRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<CarroSuspeito> veiculoOpt = carroSuspeitoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			CarroSuspeito veiculo = form.atualizar(id,carroSuspeitoRepository,
					zonaRepository, usuarioRepository);
			return ResponseEntity.ok(CarroSuspeito.converter(veiculo));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<CarroSuspeito> veiculoOpt=carroSuspeitoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			carroSuspeitoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
