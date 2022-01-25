package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api.veiculo;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoEmSituacaoIrregularForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoRoubadoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoEmSituacaoIrregularDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoRoubadoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoEmSituacaoIrregular;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoEmSituacaoIrregularRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@RestController
@RequestMapping("/api/v1/veiculos_em_situacao_irregular")
public class VeiculosEmSituacaoIrregularRest {
	
	@Autowired
	private VeiculoEmSituacaoIrregularRepository veiculoEmSituacaoIrregularRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@GetMapping
	@Cacheable(value="veículosEmSituacaoIrregular")
	public Page<VeiculoEmSituacaoIrregularDto> listar(@RequestParam(required=false) String placa,
			@PageableDefault(sort="momentoDoAlerta",direction=Direction.DESC,
			page=0,size=10) Pageable paginacao){
		//Pageable paginacao = PageRequest.of(pagina, qtde);
		if(placa==null) {
			Page<VeiculoEmSituacaoIrregular> veiculosEmSituacaoIrregular = veiculoEmSituacaoIrregularRepository.findAll(paginacao);
			
			return VeiculoEmSituacaoIrregular.converter(veiculosEmSituacaoIrregular);
		}else {
			Page<VeiculoEmSituacaoIrregular> veiculosEmSituacaoIrregular =  veiculoEmSituacaoIrregularRepository.findByPlaca(placa,
					paginacao);
			
			return VeiculoEmSituacaoIrregular.converter(veiculosEmSituacaoIrregular);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<VeiculoEmSituacaoIrregularDto> retornarEspecifico(@PathVariable("id") Long id) {
		Optional<VeiculoEmSituacaoIrregular> veiculo =  veiculoEmSituacaoIrregularRepository.findById(id);
		if(veiculo.isPresent()) {
			return ResponseEntity.ok(VeiculoEmSituacaoIrregular.converter(veiculo.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="veículosEmSituacaoIrregular",
	allEntries=true)
	public ResponseEntity<VeiculoEmSituacaoIrregularDto> cadastrar(@RequestBody @Valid VeiculoEmSituacaoIrregularForm form,
			UriComponentsBuilder uriBuilder ){
		VeiculoEmSituacaoIrregular veiculo = form.converter(zonaRepository,usuarioRepository);
		 veiculoEmSituacaoIrregularRepository.save(veiculo);
		
		URI uri = uriBuilder.path("/api/v1/veiculos_em_situacao_irregular/{id}")
				.buildAndExpand(veiculo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VeiculoEmSituacaoIrregularDto(veiculo));
	}

	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="veículosEmSituacaoIrregular",
	allEntries=true)
	public ResponseEntity<VeiculoEmSituacaoIrregularDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid VeiculoEmSituacaoIrregularForm form){
		Optional<VeiculoEmSituacaoIrregular> veiculoOpt =  veiculoEmSituacaoIrregularRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			VeiculoEmSituacaoIrregular veiculo = form.atualizar(id, veiculoEmSituacaoIrregularRepository,
					zonaRepository, usuarioRepository);
			return ResponseEntity.ok(VeiculoEmSituacaoIrregular.converter(veiculo));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="veículosEmSituacaoIrregular",
	allEntries=true)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<VeiculoEmSituacaoIrregular> veiculoOpt= veiculoEmSituacaoIrregularRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			 veiculoEmSituacaoIrregularRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
