package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.api;

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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.estrutura_devolvida.UsuarioDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.UsuarioForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.usuario.Usuario;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@RestController
@RequestMapping("${alertas_de_carros_suspeitos.api.base_servico}/usuarios")
public class UsuariosRest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Value("${alertas_de_carros_suspeitos.api.base_servico}")
	private String base_da_url_do_servico;
	
	@GetMapping
	public Page<UsuarioDto> listar(@RequestParam(required=false) String nomeDeUsuario,
			@PageableDefault(sort="createdAt", page=0, size=10, direction=Direction.DESC) Pageable paginacao){
		if(nomeDeUsuario==null) {
			Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
			return Usuario.converter(usuarios);
		}else {
			Page<Usuario> usuarios = usuarioRepository.findByNomeDeUsuario(nomeDeUsuario,paginacao);
			return Usuario.converter(usuarios);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> retornarEspecifico(@PathVariable("id") Long id){
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		
		if(usuarioOpt.isPresent()) {
			return ResponseEntity.ok(Usuario.converter(usuarioOpt.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form,
			UriComponentsBuilder uriBuilder){
		if(!form.validarZonaAssociadaEUsuarioInsersor(zonaRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		Usuario usuario = form.converter(zonaRepository, usuarioRepository);
		usuarioRepository.save(usuario);
		
		URI uri = uriBuilder.path(base_da_url_do_servico+"/usuarios/{id}")
				.buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid UsuarioForm form){
		if(!form.validarZonaAssociadaEultimoUsuarioEditor(zonaRepository, usuarioRepository)) {
			return ResponseEntity.badRequest().build();
		}
		
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		
		if(usuarioOpt.isPresent()) {
			Usuario usuario = form.atualizar(id, usuarioRepository, zonaRepository);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		
		if(usuarioOpt.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
