package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api.carro;

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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroRoubadoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.carro.CarroSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroRoubadoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.carro.CarroSuspeitoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroRoubado;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.carro.CarroSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroRoubadoRepository;

@RestController
@RequestMapping("/api/v1/carros_roubados")
public class CarrosRoubadosRest {

	@Autowired
	private CarroRoubadoRepository carroRoubadoRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	@Cacheable(value="veículosRoubados")
	public Page<CarroRoubadoDto> listar(@RequestParam(required=false) String placa,
			@PageableDefault(sort="momentoDoAlerta",direction=Direction.DESC,
			page=0,size=10) Pageable paginacao){
		//Pageable paginacao = PageRequest.of(pagina, qtde);
		if(placa==null) {
			Page<CarroRoubado> veiculosRoubados = carroRoubadoRepository.findAll(paginacao);
			
			return CarroRoubado.converter(veiculosRoubados);
		}else {
			Page<CarroRoubado> veiculosRoubados = carroRoubadoRepository.findByPlaca(placa,
					paginacao);
			
			return CarroRoubado.converter(veiculosRoubados);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroRoubadoDto> retornarEspecifico(@PathVariable("id") Long id) {
		Optional<CarroRoubado> veiculo = carroRoubadoRepository.findById(id);
		if(veiculo.isPresent()) {
			return ResponseEntity.ok(CarroRoubado.converter(veiculo.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="veículosRoubados",
	allEntries=true)
	public ResponseEntity<CarroRoubadoDto> cadastrar(@RequestBody @Valid CarroRoubadoForm form,
			UriComponentsBuilder uriBuilder ){
		CarroRoubado veiculo = form.converter(zonaRepository,usuarioRepository);
		carroRoubadoRepository.save(veiculo);
		
		URI uri = uriBuilder.path("/api/v1/veiculos_roubados/{id}")
				.buildAndExpand(veiculo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CarroRoubadoDto(veiculo));
	}

	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="veículosRoubados",
	allEntries=true)
	public ResponseEntity<CarroRoubadoDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid CarroRoubadoForm form){
		Optional<CarroRoubado> veiculoOpt = carroRoubadoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			CarroRoubado veiculo = form.atualizar(id,carroRoubadoRepository,
					zonaRepository, usuarioRepository);
			return ResponseEntity.ok(CarroRoubado.converter(veiculo));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="veículosRoubados",
	allEntries=true)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<CarroRoubado> veiculoOpt=carroRoubadoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			carroRoubadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
