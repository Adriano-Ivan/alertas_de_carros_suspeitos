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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.pendencia.TarefaParaFazerDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.pendencia.TarefaParaFazerForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.pendencia.TarefaParaFazer;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.pendencia.TarefaParaFazerRepository;

@RestController
@RequestMapping("${alertas_de_carros_suspeitos.api.base_servico}/tarefas_para_fazer")
public class TarefasParaFazerRest {

	@Autowired
	private TarefaParaFazerRepository tarefaParaFazerRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Value("${alertas_de_carros_suspeitos.api.base_servico}")
	private String base_da_url_do_servico;
	
	
	@GetMapping
	public Page<TarefaParaFazerDto> listar(@RequestParam(required=false) String descricao,
			@PageableDefault(sort="createdAt",page=0,
			size=10,direction=Direction.DESC) Pageable paginacao){
		if(descricao==null) {
			Page<TarefaParaFazer> tarefasParaFazer=tarefaParaFazerRepository.findAll(paginacao);
			return TarefaParaFazer.converter(tarefasParaFazer);
		}else {
			Page<TarefaParaFazer> tarefasParaFazer=tarefaParaFazerRepository.findByDescricao(descricao,paginacao);
			return TarefaParaFazer.converter(tarefasParaFazer);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TarefaParaFazerDto> retornarEspecifico(@PathVariable("id") Long id){
		Optional<TarefaParaFazer> tarefaParaFazer = tarefaParaFazerRepository.findById(id);
		
		if(tarefaParaFazer.isPresent()) {
			return ResponseEntity.ok(new TarefaParaFazerDto(tarefaParaFazer.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TarefaParaFazerDto> cadastrar(@RequestBody @Valid TarefaParaFazerForm form,
			UriComponentsBuilder uriBuilder){
		
		if(!form.validarUsuarioInsersor(usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		TarefaParaFazer tarefaParaFazer = form.converter(usuarioRepository);
		tarefaParaFazerRepository.save(tarefaParaFazer);
		
		URI uri = uriBuilder.path(base_da_url_do_servico+"/tarefas_para_fazer")
				.buildAndExpand(tarefaParaFazer.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TarefaParaFazerDto(tarefaParaFazer));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TarefaParaFazerDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid TarefaParaFazerForm form){
		
		if(!form.validarUltimoUsuarioEditor(usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<TarefaParaFazer> tarefaParaFazerOpt = tarefaParaFazerRepository.findById(id);
		
		if(tarefaParaFazerOpt.isPresent()) {
			TarefaParaFazer tarefaParaFazer = form.atualizar(id, tarefaParaFazerRepository,
					usuarioRepository);
			return ResponseEntity.ok(new TarefaParaFazerDto(tarefaParaFazer));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<TarefaParaFazer> tarefaParaFazerOpt = tarefaParaFazerRepository.findById(id);
		
		if(tarefaParaFazerOpt.isPresent()) {
			tarefaParaFazerRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
