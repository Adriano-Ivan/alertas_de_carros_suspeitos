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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroEmSituacaoIrregularDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroRoubadoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroEmSituacaoIrregularForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroRoubadoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroEmSituacaoIrregularRepository;

@RestController
@RequestMapping("${alertas_de_carros_suspeitos.api.base_servico}/carros_em_situacao_irregular")
public class CarrosEmSituacaoIrregularRest {
	
	@Autowired
	private CarroEmSituacaoIrregularRepository carroEmSituacaoIrregularRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Value("${alertas_de_carros_suspeitos.api.base_servico}")
	private String base_da_url_do_servico;
	
	@GetMapping
	@Cacheable(value="veículosEmSituacaoIrregular")
	public Page<CarroEmSituacaoIrregularDto> listar(@RequestParam(required=false) String placa,
			@PageableDefault(sort="momentoDoAlerta",direction=Direction.DESC,
			page=0,size=10) Pageable paginacao){
		//Pageable paginacao = PageRequest.of(pagina, qtde);
		if(placa==null) {
			Page<CarroEmSituacaoIrregular> veiculosEmSituacaoIrregular = carroEmSituacaoIrregularRepository.findAll(paginacao);
			
			return CarroEmSituacaoIrregular.converter(veiculosEmSituacaoIrregular);
		}else {
			Page<CarroEmSituacaoIrregular> veiculosEmSituacaoIrregular =  carroEmSituacaoIrregularRepository.findByPlaca(placa,
					paginacao);
			
			return CarroEmSituacaoIrregular.converter(veiculosEmSituacaoIrregular);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroEmSituacaoIrregularDto> retornarEspecifico(@PathVariable("id") Long id) {
		Optional<CarroEmSituacaoIrregular> veiculo =  carroEmSituacaoIrregularRepository.findById(id);
		if(veiculo.isPresent()) {
			return ResponseEntity.ok(CarroEmSituacaoIrregular.converter(veiculo.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="veículosEmSituacaoIrregular",
	allEntries=true)
	public ResponseEntity<CarroEmSituacaoIrregularDto> cadastrar(@RequestBody @Valid CarroEmSituacaoIrregularForm form,
			UriComponentsBuilder uriBuilder ){
		CarroEmSituacaoIrregular veiculo = form.converter(zonaRepository,usuarioRepository);
		 carroEmSituacaoIrregularRepository.save(veiculo);
		
		URI uri = uriBuilder.path(base_da_url_do_servico+"/veiculos_em_situacao_irregular/{id}")
				.buildAndExpand(veiculo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CarroEmSituacaoIrregularDto(veiculo));
	}

	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="veículosEmSituacaoIrregular",
	allEntries=true)
	public ResponseEntity<CarroEmSituacaoIrregularDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid CarroEmSituacaoIrregularForm form){
		Optional<CarroEmSituacaoIrregular> veiculoOpt =  carroEmSituacaoIrregularRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			CarroEmSituacaoIrregular veiculo = form.atualizar(id, carroEmSituacaoIrregularRepository,
					zonaRepository, usuarioRepository);
			return ResponseEntity.ok(CarroEmSituacaoIrregular.converter(veiculo));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="veículosEmSituacaoIrregular",
	allEntries=true)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<CarroEmSituacaoIrregular> veiculoOpt= carroEmSituacaoIrregularRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			 carroEmSituacaoIrregularRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
