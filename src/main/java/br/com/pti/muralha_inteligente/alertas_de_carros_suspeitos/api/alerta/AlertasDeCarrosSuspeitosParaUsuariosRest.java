package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api.alerta;

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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta.AlertaDeCarroSuspeitoParaUsuarioDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroSuspeitoParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroSuspeitoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta.AlertaDeCarroSuspeitoParaUsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroComInfracaoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroSuspeitoRepository;

@RestController
@RequestMapping("/api/v1/alertas_de_carros_suspeitos_para_usuarios")
public class AlertasDeCarrosSuspeitosParaUsuariosRest {

	@Autowired
	private AlertaDeCarroSuspeitoParaUsuarioRepository alertaDeCarroSuspeitoParaUsuarioRepository;
	
	@Autowired
	private CarroSuspeitoRepository carroComInfracaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public Page<AlertaDeCarroSuspeitoParaUsuarioDto> listar(@RequestParam(required=false) String nomeDeUsuario,
			@RequestParam(required=false) String placa,
			@PageableDefault(sort="createdAt",size=10,page=0,direction=Direction.DESC) Pageable paginacao){
		if(nomeDeUsuario !=null && placa !=null) {
			Page<AlertaDeCarroSuspeitoParaUsuario> alertas=alertaDeCarroSuspeitoParaUsuarioRepository
					.findByPlacaAndNomeDeUsuario(placa,nomeDeUsuario,paginacao);
			
			return AlertaDeCarroSuspeitoParaUsuario.converter(alertas);
		}else if(placa!=null) {
			Page<AlertaDeCarroSuspeitoParaUsuario> alertas = alertaDeCarroSuspeitoParaUsuarioRepository
					.findByPlaca(placa, paginacao);
			
			return AlertaDeCarroSuspeitoParaUsuario.converter(alertas);
		}else if(nomeDeUsuario!=null) {
			Page<AlertaDeCarroSuspeitoParaUsuario> alertas = alertaDeCarroSuspeitoParaUsuarioRepository
					.findByNomeDeUsuario( nomeDeUsuario, paginacao);
			
			return AlertaDeCarroSuspeitoParaUsuario.converter(alertas);
		}
		
		Page<AlertaDeCarroSuspeitoParaUsuario> alertas = alertaDeCarroSuspeitoParaUsuarioRepository
				.findAll(paginacao);
		
		return AlertaDeCarroSuspeitoParaUsuario.converter(alertas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlertaDeCarroSuspeitoParaUsuarioDto> retornarEspecifico(@PathVariable("id") Long id){
		Optional<AlertaDeCarroSuspeitoParaUsuario> alertaOpt = alertaDeCarroSuspeitoParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			return ResponseEntity.ok(AlertaDeCarroSuspeitoParaUsuario.converter(alertaOpt.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AlertaDeCarroSuspeitoParaUsuarioDto> cadastrar(@RequestBody @Valid 
			AlertaDeCarroSuspeitoParaUsuarioForm form,
			UriComponentsBuilder uriBuilder){
		if(!form.validarCarroSuspeitoEusuario(carroComInfracaoRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		AlertaDeCarroSuspeitoParaUsuario alerta = form.converter(carroComInfracaoRepository, usuarioRepository);
		alertaDeCarroSuspeitoParaUsuarioRepository.save(alerta);
		
		URI uri = uriBuilder.path("/api/v1/alertas_de_carros_suspeitos_para_usuarios/{id}")
				.buildAndExpand(alerta.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new AlertaDeCarroSuspeitoParaUsuarioDto(alerta));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AlertaDeCarroSuspeitoParaUsuarioDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid AlertaDeCarroSuspeitoParaUsuarioForm form){
		if(!form.validarCarroSuspeitoEusuario(carroComInfracaoRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<AlertaDeCarroSuspeitoParaUsuario> alertaOpt= alertaDeCarroSuspeitoParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			AlertaDeCarroSuspeitoParaUsuario alerta=form.atualizar(id, alertaDeCarroSuspeitoParaUsuarioRepository,
					carroComInfracaoRepository, usuarioRepository);
			return ResponseEntity.ok(new AlertaDeCarroSuspeitoParaUsuarioDto(alerta));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<AlertaDeCarroSuspeitoParaUsuario> alertaOpt=alertaDeCarroSuspeitoParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			alertaDeCarroSuspeitoParaUsuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
