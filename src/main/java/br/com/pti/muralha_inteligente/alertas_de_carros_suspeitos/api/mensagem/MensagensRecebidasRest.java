package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api.mensagem;

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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.mensagem.MensagemRecebidaDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem.MensagemRecebidaForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemRecebida;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.mensagem.MensagemRecebidaRepository;

@RestController
@RequestMapping("${alertas_de_carros_suspeitos.api.base_servico}/mensagens_recebidas")
public class MensagensRecebidasRest {

	@Autowired
	private MensagemRecebidaRepository mensagemRecebidaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public Page<MensagemRecebidaDto> listar(@RequestParam(required=false) String mensagem,
			@PageableDefault(sort="createdAt",page=0,size=10,
			direction=Direction.DESC) Pageable paginacao){
		if(mensagem==null) {
			Page<MensagemRecebida> mensagensRecebidas = mensagemRecebidaRepository.findAll(paginacao);
			return MensagemRecebida.converter(mensagensRecebidas);
		}else {
			Page<MensagemRecebida> mensagensRecebidas = mensagemRecebidaRepository.findByMensagem(mensagem,paginacao);
			return MensagemRecebida.converter(mensagensRecebidas);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MensagemRecebidaDto> retornarEspecifico(@PathVariable("id") Long id){
		Optional<MensagemRecebida> mensagemRecebida = mensagemRecebidaRepository.findById(id);
		
		if(mensagemRecebida.isPresent()) {
			return ResponseEntity.ok(MensagemRecebida.converter(mensagemRecebida.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MensagemRecebidaDto> cadastrar(@RequestBody @Valid MensagemRecebidaForm form,
			UriComponentsBuilder uriBuilder){
		MensagemRecebida mensagemRecebida = form.converter(usuarioRepository);
		mensagemRecebidaRepository.save(mensagemRecebida);
		
		URI uri = uriBuilder.path("${alertas_de_carros_suspeitos.api.base_servico}/mensagens_recebidas/{id}")
				.buildAndExpand(mensagemRecebida.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new MensagemRecebidaDto(mensagemRecebida));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MensagemRecebidaDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid MensagemRecebidaForm form){
		Optional<MensagemRecebida> mensagemOpt = mensagemRecebidaRepository.findById(id);
		
		if(mensagemOpt.isPresent()) {
			MensagemRecebida mensagem = form.atualizar(id, mensagemRecebidaRepository,
					usuarioRepository);
			
			return ResponseEntity.ok(MensagemRecebida.converter(mensagem));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<MensagemRecebida> mensagemOpt = mensagemRecebidaRepository.findById(id);
		
		if(mensagemOpt.isPresent()) {
			mensagemRecebidaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
