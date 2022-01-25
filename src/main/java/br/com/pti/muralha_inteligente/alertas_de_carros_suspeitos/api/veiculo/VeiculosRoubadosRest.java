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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoRoubadoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoSuspeitoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoRoubadoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoRoubadoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@RestController
@RequestMapping("/api/v1/veiculos_roubados")
public class VeiculosRoubadosRest {

	@Autowired
	private VeiculoRoubadoRepository veiculoRoubadoRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	@Cacheable(value="veículosRoubados")
	public Page<VeiculoRoubadoDto> listar(@RequestParam(required=false) String placa,
			@PageableDefault(sort="momentoDoAlerta",direction=Direction.DESC,
			page=0,size=10) Pageable paginacao){
		//Pageable paginacao = PageRequest.of(pagina, qtde);
		if(placa==null) {
			Page<VeiculoRoubado> veiculosRoubados = veiculoRoubadoRepository.findAll(paginacao);
			
			return VeiculoRoubado.converter(veiculosRoubados);
		}else {
			Page<VeiculoRoubado> veiculosRoubados = veiculoRoubadoRepository.findByPlaca(placa,
					paginacao);
			
			return VeiculoRoubado.converter(veiculosRoubados);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<VeiculoRoubadoDto> retornarEspecifico(@PathVariable("id") Long id) {
		Optional<VeiculoRoubado> veiculo = veiculoRoubadoRepository.findById(id);
		if(veiculo.isPresent()) {
			return ResponseEntity.ok(VeiculoRoubado.converter(veiculo.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="veículosRoubados",
	allEntries=true)
	public ResponseEntity<VeiculoRoubadoDto> cadastrar(@RequestBody @Valid VeiculoRoubadoForm form,
			UriComponentsBuilder uriBuilder ){
		VeiculoRoubado veiculo = form.converter(zonaRepository,usuarioRepository);
		veiculoRoubadoRepository.save(veiculo);
		
		URI uri = uriBuilder.path("/api/v1/veiculos_roubados/{id}")
				.buildAndExpand(veiculo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VeiculoRoubadoDto(veiculo));
	}

	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="veículosRoubados",
	allEntries=true)
	public ResponseEntity<VeiculoRoubadoDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid VeiculoRoubadoForm form){
		Optional<VeiculoRoubado> veiculoOpt = veiculoRoubadoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			VeiculoRoubado veiculo = form.atualizar(id,veiculoRoubadoRepository,
					zonaRepository, usuarioRepository);
			return ResponseEntity.ok(VeiculoRoubado.converter(veiculo));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="veículosRoubados",
	allEntries=true)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<VeiculoRoubado> veiculoOpt=veiculoRoubadoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			veiculoRoubadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
