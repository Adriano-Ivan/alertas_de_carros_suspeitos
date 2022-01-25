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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.BotDoTelegramDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.BotDoTelegramForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.BotDoTelegram;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.BotDoTelegramRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@RestController
@RequestMapping("/api/v1/bots_do_telegram")
public class BotsDoTelegramRest {

	@Autowired
	private BotDoTelegramRepository botDoTelegramRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@GetMapping
	public Page<BotDoTelegramDto> listar(@RequestParam(required=false) String denominacao, 
			@PageableDefault(sort="createdAt",page=0,
			size=10,direction=Direction.DESC) Pageable paginacao){
		if(denominacao==null) {
			Page<BotDoTelegram> botsDoTelegram = botDoTelegramRepository.findAll(paginacao);
			return BotDoTelegram.converter(botsDoTelegram);
		}else {
			Page<BotDoTelegram> botsDoTelegram =botDoTelegramRepository.findByDenominacao(denominacao,paginacao);
			return BotDoTelegram.converter(botsDoTelegram);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BotDoTelegramDto> retornarEspecifico(@PathVariable("id") Long id){
		Optional<BotDoTelegram> botDoTelegramOpt = botDoTelegramRepository.findById(id);
		
		if(botDoTelegramOpt.isPresent()) {
			return ResponseEntity.ok(BotDoTelegram.converter(botDoTelegramOpt.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<BotDoTelegramDto> cadastrar(@RequestBody @Valid BotDoTelegramForm form,
			UriComponentsBuilder uriBuilder){
		BotDoTelegram botDoTelegram = form.converter(zonaRepository);
		botDoTelegramRepository.save(botDoTelegram);
		
		URI uri = uriBuilder.path("/api/v1/bots_do_telgram/{id})")
				.buildAndExpand(botDoTelegram.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new BotDoTelegramDto(botDoTelegram));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<BotDoTelegramDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid BotDoTelegramForm form){
		Optional<BotDoTelegram> botDoTelegramOpt = botDoTelegramRepository.findById(id);
		
		if(botDoTelegramOpt.isPresent()) {
			BotDoTelegram botDoTelegram = form.atualizar(id, botDoTelegramRepository, zonaRepository);
			return ResponseEntity.ok(new BotDoTelegramDto(botDoTelegram));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<BotDoTelegram> botDoTelegramOpt = botDoTelegramRepository.findById(id);
		
		if(botDoTelegramOpt.isPresent()) {
			botDoTelegramRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
