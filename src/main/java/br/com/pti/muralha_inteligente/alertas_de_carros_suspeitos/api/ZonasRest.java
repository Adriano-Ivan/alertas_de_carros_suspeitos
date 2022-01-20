package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api;

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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.ZonaDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.ZonaForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.Zona;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@RestController
@RequestMapping("/api/zonas")
public class ZonasRest {

	@Autowired
	private ZonaRepository zonaRepository;
	
	@GetMapping
	@Cacheable(value="listarZonas")
	public Page<ZonaDto> listar(@RequestParam(required=false) String zona,
			@PageableDefault(page=0,size=10,
			sort="zona",direction=Direction.ASC) Pageable paginacao){
		if(zona == null) {
			Page<Zona> zonas = zonaRepository.findAll(paginacao);
			
			return Zona.converter(zonas);
		}else {
			Page<Zona> zonas = zonaRepository.findByZona(zona,paginacao);
			
			return Zona.converter(zonas);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ZonaDto> retornarEspecifo(@PathVariable("id") Long id){
		Optional<Zona> zona = zonaRepository.findById(id);
		if(zona.isPresent()) {
			return ResponseEntity.ok(Zona.converter(zona.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value="listarZonas")
	public ResponseEntity<ZonaDto> cadastrar(@RequestBody @Valid ZonaForm form,
			UriComponentsBuilder uriBuilder){
		Zona zona = form.converter();
		zonaRepository.save(zona);
		
		URI uri = uriBuilder.path("/api/zonas/{id}")
				.buildAndExpand(zona.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ZonaDto(zona));
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="listarZonas")
	public ResponseEntity<ZonaDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid ZonaForm form){
		Optional<Zona> zonaOpt = zonaRepository.findById(id);
		
		if(zonaOpt.isPresent()) {
			Zona zona = form.atualizar(id,zonaRepository);
			return ResponseEntity.ok(Zona.converter(zona));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="listarZonas")
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<Zona> zona = zonaRepository.findById(id);
		
		if(zona.isPresent()) {
			zonaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}