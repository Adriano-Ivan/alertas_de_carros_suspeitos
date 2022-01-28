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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.alerta.AlertaDeCarroRoubadoParaUsuarioDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.alerta.AlertaDeCarroRoubadoParaUsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.alertas.AlertaDeCarroRoubadoParaUsuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.alerta.AlertaDeCarroRoubadoParaUsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.carro.CarroRoubadoRepository;

@RestController
@RequestMapping("/api/v1/alertas_de_carros_roubados_para_usuarios")
public class AlertasDeCarrosRoubadosParaUsuariosRest {

	@Autowired
	private AlertaDeCarroRoubadoParaUsuarioRepository alertaDeCarroRoubadoParaUsuarioRepository;
	
	@Autowired
	private CarroRoubadoRepository carroRoubadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public Page<AlertaDeCarroRoubadoParaUsuarioDto> listar(@RequestParam(required=false) String placa,
			@RequestParam(required=false) String nomeDeUsuario,
			@PageableDefault(sort="createdAt",page=0,size=10,direction=Direction.DESC) Pageable paginacao){
		if(placa != null && nomeDeUsuario != null) {
			Page<AlertaDeCarroRoubadoParaUsuario> alertas = alertaDeCarroRoubadoParaUsuarioRepository
					.findByPlacaAndNomeDeUsuario(placa,nomeDeUsuario,paginacao);
			
			return AlertaDeCarroRoubadoParaUsuario.converter(alertas);
		} else if(placa !=null) {
			Page<AlertaDeCarroRoubadoParaUsuario> alertas=alertaDeCarroRoubadoParaUsuarioRepository
					.findByPlaca(placa, paginacao);
			return AlertaDeCarroRoubadoParaUsuario.converter(alertas);
		} else if(nomeDeUsuario!=null) {
			Page<AlertaDeCarroRoubadoParaUsuario> alertas = alertaDeCarroRoubadoParaUsuarioRepository
					.findByNomeDeUsuario( nomeDeUsuario, paginacao);
			return AlertaDeCarroRoubadoParaUsuario.converter(alertas);
		}
		
		Page<AlertaDeCarroRoubadoParaUsuario> alertas=alertaDeCarroRoubadoParaUsuarioRepository
				.findAll(paginacao);
		return AlertaDeCarroRoubadoParaUsuario.converter(alertas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlertaDeCarroRoubadoParaUsuarioDto> retornarEspecifico(@PathVariable("id") Long id){
		Optional<AlertaDeCarroRoubadoParaUsuario> alertaOpt = alertaDeCarroRoubadoParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			return ResponseEntity.ok(AlertaDeCarroRoubadoParaUsuario.converter(alertaOpt.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AlertaDeCarroRoubadoParaUsuarioDto> cadastrar(@RequestBody @Valid 
			AlertaDeCarroRoubadoParaUsuarioForm form,
			UriComponentsBuilder uriBuilder){
		if(!form.validarCarroRoubadoEusuario(carroRoubadoRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		AlertaDeCarroRoubadoParaUsuario alerta = form.converter(carroRoubadoRepository, usuarioRepository);
		alertaDeCarroRoubadoParaUsuarioRepository.save(alerta);
		
		URI uri = uriBuilder.path("/api/v1/alertas_de_carros_roubados_para_usuarios/{id}")
				.buildAndExpand(alerta.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new AlertaDeCarroRoubadoParaUsuarioDto(alerta));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AlertaDeCarroRoubadoParaUsuarioDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid AlertaDeCarroRoubadoParaUsuarioForm form){
		if(!form.validarCarroRoubadoEusuario(carroRoubadoRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<AlertaDeCarroRoubadoParaUsuario> alertaOpt = alertaDeCarroRoubadoParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			AlertaDeCarroRoubadoParaUsuario alerta=form.atualizar(id, alertaDeCarroRoubadoParaUsuarioRepository,
					carroRoubadoRepository, usuarioRepository);
			return ResponseEntity.ok(new AlertaDeCarroRoubadoParaUsuarioDto(alerta));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<AlertaDeCarroRoubadoParaUsuario> alertaOpt=alertaDeCarroRoubadoParaUsuarioRepository
				.findById(id);
		
		if(alertaOpt.isPresent()) {
			alertaDeCarroRoubadoParaUsuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
