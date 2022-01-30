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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.BotDoTelegramDeUsuarioDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.BotDoTelegramDeUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.BotDoTelegramDeUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.BotDoTelegramDeUsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.BotDoTelegramRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;

@RestController
@RequestMapping("${alertas_de_carros_suspeitos.api.base_servico}/bots_do_telegram_de_usuarios")
public class BotsDoTelegramDeUsuariosRest {

	@Autowired
	private BotDoTelegramDeUsuarioRepository botDoTelegramDeUsuarioRepository;
	
	@Autowired
	private BotDoTelegramRepository botDoTelegramRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public Page<BotDoTelegramDeUsuarioDto> listar(@RequestParam(required=false) String nomeDeUsuario,   
			@RequestParam(required=false) String denominacaoDoBotDoTelegram,  
			@PageableDefault(sort="createdAt",page=0,size=10,direction=Direction.DESC) Pageable paginacao){
		if(nomeDeUsuario!=null && denominacaoDoBotDoTelegram != null) {
			Page<BotDoTelegramDeUsuario> botsDoTelegramDeUsuarios = botDoTelegramDeUsuarioRepository
					.findByNomeDoUsuarioAndNomeDoBot(nomeDeUsuario, denominacaoDoBotDoTelegram,paginacao);
			
			return BotDoTelegramDeUsuario.converter(botsDoTelegramDeUsuarios);
		} else if(nomeDeUsuario !=null) {
			Page<BotDoTelegramDeUsuario> botsDoTelegramDeUsuarios = botDoTelegramDeUsuarioRepository
					.findByNomeDoUsuario(nomeDeUsuario, paginacao);
			
			return BotDoTelegramDeUsuario.converter(botsDoTelegramDeUsuarios);
		} else if(denominacaoDoBotDoTelegram!=null) {
			Page<BotDoTelegramDeUsuario> botsDoTelegramDeUsuarios = botDoTelegramDeUsuarioRepository
					.findByNomeDoBot(denominacaoDoBotDoTelegram, paginacao);
			
			return BotDoTelegramDeUsuario.converter(botsDoTelegramDeUsuarios);
		}
		
		Page<BotDoTelegramDeUsuario> botsDoTelegramDeUsuarios = botDoTelegramDeUsuarioRepository
				.findAll(paginacao);
		return BotDoTelegramDeUsuario.converter(botsDoTelegramDeUsuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BotDoTelegramDeUsuarioDto> retornarEspecifico(@PathVariable("id") Long id){
		Optional<BotDoTelegramDeUsuario> botDoTelegramDeUsuario = botDoTelegramDeUsuarioRepository
				.findById(id);
		
		if(botDoTelegramDeUsuario.isPresent()) {
			return ResponseEntity.ok(BotDoTelegramDeUsuario.converter(botDoTelegramDeUsuario.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<BotDoTelegramDeUsuarioDto> cadastrar(@RequestBody @Valid BotDoTelegramDeUsuarioForm 
			form, UriComponentsBuilder uriBuilder){
		if(!form.validarBotDoTelegramEusuario(botDoTelegramRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		BotDoTelegramDeUsuario botDoTelegramDeUsuario = form.converter(botDoTelegramRepository, usuarioRepository);
		botDoTelegramDeUsuarioRepository.save(botDoTelegramDeUsuario);
		URI uri = uriBuilder.path("${alertas_de_carros_suspeitos.api.base_servico}/bots_do_telegram_de_usuarios/{id}")
				.buildAndExpand(botDoTelegramDeUsuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new BotDoTelegramDeUsuarioDto(botDoTelegramDeUsuario));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<BotDoTelegramDeUsuarioDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid BotDoTelegramDeUsuarioForm 
			form){
		if(!form.validarBotDoTelegramEusuario(botDoTelegramRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<BotDoTelegramDeUsuario> botDoTelegramDeUsuarioOpt = botDoTelegramDeUsuarioRepository
				.findById(id);
		
		if(botDoTelegramDeUsuarioOpt.isPresent()) {
			BotDoTelegramDeUsuario botDoTelegramDeUsuario = form.atualizar(id, botDoTelegramDeUsuarioRepository,
					botDoTelegramRepository, usuarioRepository);
			return ResponseEntity.ok(new BotDoTelegramDeUsuarioDto(botDoTelegramDeUsuario));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<BotDoTelegramDeUsuario> botDoTelegramDeUsuarioOpt = botDoTelegramDeUsuarioRepository
				.findById(id);
		
		if(botDoTelegramDeUsuarioOpt.isPresent()) {
			botDoTelegramDeUsuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
