package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api.carro;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroComInfracaoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroComInfracaoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroSuspeitoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroComInfracaoRepository;

@RestController
@RequestMapping("${alertas_de_carros_suspeitos.api.base_servico}/carros_com_infracao")
public class CarrosComInfracao {

	@Autowired
	private CarroComInfracaoRepository carroComInfracaoRepository;
	
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Value("${alertas_de_carros_suspeitos.api.base_servico}")
	private String base_da_url_do_servico;
	
	@GetMapping
	public Page<CarroComInfracaoDto> listar(@RequestParam(required=false) String placa,
			@PageableDefault(sort="momentoDoAlerta",direction=Direction.DESC,
			page=0,size=10) Pageable paginacao){
		//Pageable paginacao = PageRequest.of(pagina, qtde);
		if(placa==null) {
			Page<CarroComInfracao> veiculosSuspeitos = carroComInfracaoRepository.findAll(paginacao);
			
			return CarroComInfracao.converter(veiculosSuspeitos);
		}else {
			Page<CarroComInfracao> veiculosSuspeitos = carroComInfracaoRepository.findByPlaca(placa,
					paginacao);
			
			return CarroComInfracao.converter(veiculosSuspeitos);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroComInfracaoDto> retornarEspecifico(@PathVariable("id") Long id) {
		Optional<CarroComInfracao> veiculo = carroComInfracaoRepository.findById(id);
		if(veiculo.isPresent()) {
			return ResponseEntity.ok(CarroComInfracao.converter(veiculo.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CarroComInfracaoDto> cadastrar(@RequestBody @Valid CarroComInfracaoForm form,
			UriComponentsBuilder uriBuilder ){
		CarroComInfracao veiculo = form.converter(zonaRepository,usuarioRepository);
		carroComInfracaoRepository.save(veiculo);
		
		URI uri = uriBuilder.path(base_da_url_do_servico+"/veiculos_com_infracao/{id}")
				.buildAndExpand(veiculo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CarroComInfracaoDto(veiculo));
	}

	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CarroComInfracaoDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid CarroComInfracaoForm form){
		Optional<CarroComInfracao> veiculoOpt = carroComInfracaoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			CarroComInfracao veiculo = form.atualizar(id,carroComInfracaoRepository,
					zonaRepository, usuarioRepository);
			return ResponseEntity.ok(CarroComInfracao.converter(veiculo));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<CarroComInfracao> veiculoOpt=carroComInfracaoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			carroComInfracaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
