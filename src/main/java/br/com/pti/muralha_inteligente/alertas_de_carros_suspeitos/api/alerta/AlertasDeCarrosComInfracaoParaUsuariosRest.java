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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta.AlertaDeCarroComInfracaoParaUsuarioDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroComInfracaoParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroComInfracaoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta.AlertaDeCarroComInfracaoParaUsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroComInfracaoRepository;

@RestController
@RequestMapping("/api/v1/alertas_de_carros_com_infracao_para_usuarios")
public class AlertasDeCarrosComInfracaoParaUsuariosRest {

	@Autowired
	private AlertaDeCarroComInfracaoParaUsuarioRepository alertaDeCarroComInfracaoParaUsuarioRepository;
	
	@Autowired
	private CarroComInfracaoRepository carroComInfracaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public Page<AlertaDeCarroComInfracaoParaUsuarioDto> listar(@RequestParam(required=false) String placa,
			@RequestParam(required=false) String nomeDeUsuario,
			@PageableDefault(size=10,sort="createdAt",
			page=0, direction=Direction.DESC)Pageable paginacao){
		if(placa != null && nomeDeUsuario != null) {
			Page<AlertaDeCarroComInfracaoParaUsuario> alertas = alertaDeCarroComInfracaoParaUsuarioRepository
					.findByPlacaAndNomeDeUsuario(placa,nomeDeUsuario,paginacao);
			
			return AlertaDeCarroComInfracaoParaUsuario.converter(alertas);
		} else if(placa != null) {
			Page<AlertaDeCarroComInfracaoParaUsuario> alertas = alertaDeCarroComInfracaoParaUsuarioRepository
					.findByPlaca(placa,paginacao);
			
			return AlertaDeCarroComInfracaoParaUsuario.converter(alertas);
		} else if(nomeDeUsuario !=null) {
			Page<AlertaDeCarroComInfracaoParaUsuario> alertas = alertaDeCarroComInfracaoParaUsuarioRepository
					.findByNomeDeUsuario(nomeDeUsuario,paginacao);
			
			return AlertaDeCarroComInfracaoParaUsuario.converter(alertas);
		}
		
		Page<AlertaDeCarroComInfracaoParaUsuario> alertas = alertaDeCarroComInfracaoParaUsuarioRepository.findAll(paginacao);
		return AlertaDeCarroComInfracaoParaUsuario.converter(alertas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlertaDeCarroComInfracaoParaUsuarioDto> retornarEspecifico(@PathVariable("id") Long id){
		Optional<AlertaDeCarroComInfracaoParaUsuario> alertaOpt = alertaDeCarroComInfracaoParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			return ResponseEntity.ok(AlertaDeCarroComInfracaoParaUsuario.converter(alertaOpt.get()));
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AlertaDeCarroComInfracaoParaUsuarioDto> cadastrar(@RequestBody @Valid 
			AlertaDeCarroComInfracaoParaUsuarioForm form,
			UriComponentsBuilder uriBuilder){
		if(!form.validarCarroComInfracaoEusuario(carroComInfracaoRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
	
		AlertaDeCarroComInfracaoParaUsuario alerta = form.converter(carroComInfracaoRepository, usuarioRepository);
		alertaDeCarroComInfracaoParaUsuarioRepository.save(alerta);
		
		URI uri = uriBuilder.path("/api/v1/alertas_de_carros_com_infracao_para_usuarios/{id}")
				.buildAndExpand(alerta.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new AlertaDeCarroComInfracaoParaUsuarioDto(alerta));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AlertaDeCarroComInfracaoParaUsuarioDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid AlertaDeCarroComInfracaoParaUsuarioForm form){
		if(!form.validarCarroComInfracaoEusuario(carroComInfracaoRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<AlertaDeCarroComInfracaoParaUsuario> alertaOpt= alertaDeCarroComInfracaoParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			AlertaDeCarroComInfracaoParaUsuario alerta = form.atualizar(id, alertaDeCarroComInfracaoParaUsuarioRepository, carroComInfracaoRepository,
					usuarioRepository);
			
			return ResponseEntity.ok(new AlertaDeCarroComInfracaoParaUsuarioDto(alerta));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<AlertaDeCarroComInfracaoParaUsuario> alertaOpt = alertaDeCarroComInfracaoParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			alertaDeCarroComInfracaoParaUsuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
