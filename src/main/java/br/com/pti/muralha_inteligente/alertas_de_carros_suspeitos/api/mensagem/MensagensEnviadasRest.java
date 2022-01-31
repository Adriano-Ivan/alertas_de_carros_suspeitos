package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api.mensagem;

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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.mensagem.MensagemEnviadaDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.mensagem.MensagemEnviadaForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.mensagem.MensagemEnviada;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.mensagem.MensagemEnviadaRepository;

@RestController
@RequestMapping("${alertas_de_carros_suspeitos.api.base_servico}/mensagens_enviadas")
public class MensagensEnviadasRest {

	@Autowired
	private MensagemEnviadaRepository mensagemEnviadaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Value("${alertas_de_carros_suspeitos.api.base_servico}")
	private String base_da_url_do_servico;
	
	
	@GetMapping
	public Page<MensagemEnviadaDto> listar(@RequestParam(required=false) String mensagem,
			@PageableDefault(sort="createdAt",page=0,
			size=10,direction=Direction.DESC) Pageable paginacao){
		if(mensagem==null) {
			Page<MensagemEnviada> mensagensEnviadas = mensagemEnviadaRepository.findAll(paginacao);
			return MensagemEnviada.converter(mensagensEnviadas);
		}else {
			Page<MensagemEnviada> mensagensEnviadas = mensagemEnviadaRepository.findByMensagem(mensagem,paginacao);
			return MensagemEnviada.converter(mensagensEnviadas);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MensagemEnviadaDto> encontrarEspecifico(@PathVariable("id") Long id){
		Optional<MensagemEnviada> mensagemOpt = mensagemEnviadaRepository.findById(id);
		
		if(mensagemOpt.isPresent()) {
			return ResponseEntity.ok(MensagemEnviada.converter(mensagemOpt.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MensagemEnviadaDto> cadastrar(@RequestBody @Valid MensagemEnviadaForm form, 
			UriComponentsBuilder uriBuilder){
		
		if(!form.validarUsuario(usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		MensagemEnviada mensagem = form.converter(usuarioRepository);
		mensagemEnviadaRepository.save(mensagem);
		
		URI uri = uriBuilder.path(base_da_url_do_servico+"/mensagens_enviadas/{id}")
				.buildAndExpand(mensagem.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new MensagemEnviadaDto(mensagem));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MensagemEnviadaDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid MensagemEnviadaForm form){
		
		if(!form.validarUsuario(usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<MensagemEnviada> mensagemOpt=mensagemEnviadaRepository.findById(id);
		
		if(mensagemOpt.isPresent()) {
			MensagemEnviada mensagem=form.atualizar(id, mensagemEnviadaRepository, usuarioRepository);
			return ResponseEntity.ok(MensagemEnviada.converter(mensagem));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id){
		Optional<MensagemEnviada> mensagemOpt= mensagemEnviadaRepository.findById(id);
		
		if(mensagemOpt.isPresent()) {
			mensagemEnviadaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}

