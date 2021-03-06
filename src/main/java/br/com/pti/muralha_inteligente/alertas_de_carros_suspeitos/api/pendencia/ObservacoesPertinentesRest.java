package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api.pendencia;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.pendencia.ObservacaoPertinenteDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.pendencia.ObservacaoPertinenteForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.ObservacaoPertinente;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.pendencia.ObservacaoPertinenteRepository;

@RestController
@RequestMapping("${alertas_de_carros_suspeitos.api.base_servico}/observacoes_pertinentes")
public class ObservacoesPertinentesRest {

	@Autowired
	private ObservacaoPertinenteRepository observacaoPertinenteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Value("${alertas_de_carros_suspeitos.api.base_servico}")
	private String base_da_url_do_servico;
	
	
	@GetMapping
	public Page<ObservacaoPertinenteDto> listar(@RequestParam(required=false) String descricao,
			@PageableDefault(sort="createdAt",size=10,
			page=0,direction=Direction.DESC) Pageable paginacao){
		if(descricao==null) {
			Page<ObservacaoPertinente> observacoesPertinentes = observacaoPertinenteRepository.findAll(paginacao);
			return ObservacaoPertinente.converter(observacoesPertinentes);
		}else {
			Page<ObservacaoPertinente> observacoesPertinentes = observacaoPertinenteRepository.findByDescricao(descricao,paginacao);
			return ObservacaoPertinente.converter(observacoesPertinentes);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ObservacaoPertinenteDto> retornarEspecifico(@PathVariable("id") Long id){
		Optional<ObservacaoPertinente> observacaoPertinente = observacaoPertinenteRepository.findById(id);
		
		if(observacaoPertinente.isPresent()) {
			return ResponseEntity.ok(ObservacaoPertinente.converter(observacaoPertinente.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ObservacaoPertinenteDto> cadastrar(@RequestBody @Valid ObservacaoPertinenteForm form,
			UriComponentsBuilder uriBuilder){
		
		if(!form.validarUsuarioInsersor(usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		ObservacaoPertinente observacaoPertinente = form.converter(usuarioRepository);
		observacaoPertinenteRepository.save(observacaoPertinente);
		
		URI uri = uriBuilder.path(base_da_url_do_servico+"/observacoes_pertinentes/{id}")
				.buildAndExpand(observacaoPertinente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ObservacaoPertinenteDto(observacaoPertinente));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ObservacaoPertinenteDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid ObservacaoPertinenteForm form){
		
		if(!form.validarUltimoUsuarioEditor(usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<ObservacaoPertinente> observacaoPertinenteOpt = observacaoPertinenteRepository.findById(id);
		
		if(observacaoPertinenteOpt.isPresent()) {
			ObservacaoPertinente observacaoPertinente = form.atualizar(id, observacaoPertinenteRepository,
					usuarioRepository);
			return ResponseEntity.ok(new ObservacaoPertinenteDto(observacaoPertinente));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<ObservacaoPertinente> observacaoPertinenteOpt = observacaoPertinenteRepository.findById(id);
		
		if(observacaoPertinenteOpt.isPresent()) {
			observacaoPertinenteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
