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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta.AlertaDeCarroEmSituacaoIrregularParaUsuarioDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroEmSituacaoIrregularParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroEmSituacaoIrregularParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta.AlertaDeCarroEmSituacaoIrregularParaUsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroEmSituacaoIrregularRepository;

@RestController
@RequestMapping("${alertas_de_carros_suspeitos.api.base_servico}/alertas_de_carros_em_situacao_irregular_para_usuarios")
public class AlertasDeCarrosEmSituacaoIrregularParaUsuariosRest {

	@Autowired
	private AlertaDeCarroEmSituacaoIrregularParaUsuarioRepository alertaDeCarroEmSituacaoIrregularParaUsuarioRepository;
	
	@Autowired
	private CarroEmSituacaoIrregularRepository carroEmSituacaoIrregualarRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public Page<AlertaDeCarroEmSituacaoIrregularParaUsuarioDto> listar(@RequestParam(required=false) String placa,  
			@RequestParam(required=false) String nomeDeUsuario,
			@PageableDefault(sort="createdAt", size=10, page=0,direction=Direction.DESC) Pageable paginacao){
		if(placa != null && nomeDeUsuario != null) {
			Page<AlertaDeCarroEmSituacaoIrregularParaUsuario> alertas = alertaDeCarroEmSituacaoIrregularParaUsuarioRepository
					.findByPlacaAndNomeDeUsuario(placa,nomeDeUsuario,paginacao);
			return AlertaDeCarroEmSituacaoIrregularParaUsuario.converter(alertas);
		} else if(placa !=null) {
			Page<AlertaDeCarroEmSituacaoIrregularParaUsuario> alertas = alertaDeCarroEmSituacaoIrregularParaUsuarioRepository
					.findByPlaca(placa,paginacao);
			return AlertaDeCarroEmSituacaoIrregularParaUsuario.converter(alertas);
		} else if(nomeDeUsuario !=null) {
			Page<AlertaDeCarroEmSituacaoIrregularParaUsuario> alertas = alertaDeCarroEmSituacaoIrregularParaUsuarioRepository
					.findByNomeDeUsuario(nomeDeUsuario, paginacao);
		}
		
		Page<AlertaDeCarroEmSituacaoIrregularParaUsuario> alertas = alertaDeCarroEmSituacaoIrregularParaUsuarioRepository
				.findAll(paginacao);
		return AlertaDeCarroEmSituacaoIrregularParaUsuario.converter(alertas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlertaDeCarroEmSituacaoIrregularParaUsuarioDto> retornarEspecifico(@PathVariable("id") Long id){
		Optional<AlertaDeCarroEmSituacaoIrregularParaUsuario> alertaOpt = alertaDeCarroEmSituacaoIrregularParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			return ResponseEntity.ok(AlertaDeCarroEmSituacaoIrregularParaUsuario.converter(alertaOpt.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AlertaDeCarroEmSituacaoIrregularParaUsuarioDto> cadastrar(@RequestBody @Valid
			AlertaDeCarroEmSituacaoIrregularParaUsuarioForm form,  
			UriComponentsBuilder uriBuilder){
		if(!form.validarCarroEmSituacaoIrregularEusuario(carroEmSituacaoIrregualarRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		AlertaDeCarroEmSituacaoIrregularParaUsuario alerta = form.converter(carroEmSituacaoIrregualarRepository,
				usuarioRepository);
		alertaDeCarroEmSituacaoIrregularParaUsuarioRepository.save(alerta);
		
		URI uri = uriBuilder.path("${alertas_de_carros_suspeitos.api.base_servico}/alertas_de_carros_em_situacao_irregular_para_usuarios/{id}")
				.buildAndExpand(alerta.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new AlertaDeCarroEmSituacaoIrregularParaUsuarioDto(alerta));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AlertaDeCarroEmSituacaoIrregularParaUsuarioDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid AlertaDeCarroEmSituacaoIrregularParaUsuarioForm form ){
		if(!form.validarCarroEmSituacaoIrregularEusuario(carroEmSituacaoIrregualarRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<AlertaDeCarroEmSituacaoIrregularParaUsuario> alertaOpt =alertaDeCarroEmSituacaoIrregularParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			AlertaDeCarroEmSituacaoIrregularParaUsuario alerta = form.atualizar(id, alertaDeCarroEmSituacaoIrregularParaUsuarioRepository,
					carroEmSituacaoIrregualarRepository, usuarioRepository);
			return ResponseEntity.ok(new AlertaDeCarroEmSituacaoIrregularParaUsuarioDto(alerta));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<AlertaDeCarroEmSituacaoIrregularParaUsuario> alertaOpt=alertaDeCarroEmSituacaoIrregularParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			alertaDeCarroEmSituacaoIrregularParaUsuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
