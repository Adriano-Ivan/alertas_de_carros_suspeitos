package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.LocalAlvoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.LocalAlvoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.LocalAlvo;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.LocalAlvoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@RestController
@RequestMapping("/api/v1/locais_alvo")
public class LocaisAlvoRest {
	
	@Autowired
	private LocalAlvoRepository localAlvoRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public Page<LocalAlvoDto> listar(@RequestParam(required=false) String local,
			@PageableDefault(sort="createdAt",page=0,size=10,
			direction=Direction.DESC) Pageable paginacao){
		if(local==null) {
			Page<LocalAlvo> locaisAlvo = localAlvoRepository.findAll(paginacao);
			return LocalAlvo.converter(locaisAlvo);
		}else {
			Page<LocalAlvo> locaisAlvo=localAlvoRepository.findByLocal(local,paginacao);
			return LocalAlvo.converter(locaisAlvo);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LocalAlvoDto> retornarEspecifico(@PathVariable("id") Long id){
		Optional<LocalAlvo> localAlvoOpt = localAlvoRepository.findById(id);
		
		if(localAlvoOpt.isPresent()) {
			return ResponseEntity.ok(LocalAlvo.converter(localAlvoOpt.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<LocalAlvoDto> cadastrar(@RequestBody @Valid LocalAlvoForm form,
			UriComponentsBuilder uriBuilder){
		LocalAlvo localAlvo = form.converter(zonaRepository, usuarioRepository);
		localAlvoRepository.save(localAlvo);
		
		URI uri = uriBuilder.path("/api/v1/locais_alvo/{id}")
				.buildAndExpand(localAlvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new LocalAlvoDto(localAlvo));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<LocalAlvoDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid LocalAlvoForm form){
		Optional<LocalAlvo> localAlvoOpt = localAlvoRepository.findById(id);
		
		if(localAlvoOpt.isPresent()) {
			LocalAlvo localAlvo = form.atualizar(id, localAlvoRepository, zonaRepository, usuarioRepository);
			return ResponseEntity.ok(new LocalAlvoDto(localAlvo));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<LocalAlvo> localAlvoOpt = localAlvoRepository.findById(id);
		
		if(localAlvoOpt.isPresent()) {
			localAlvoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
