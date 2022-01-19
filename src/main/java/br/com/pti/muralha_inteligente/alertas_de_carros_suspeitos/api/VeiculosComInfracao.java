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

import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoComInfracaoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.form.veiculo.VeiculoSuspeitoForm;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoComInfracaoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.dto.veiculo.VeiculoSuspeitoDto;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoComInfracao;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.model.veiculo.VeiculoSuspeito;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.UsuarioRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.VeiculoComInfracaoRepository;
import br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos.repository.ZonaRepository;

@RestController
@RequestMapping("/api/veiculos_com_infracao")
public class VeiculosComInfracao {

	@Autowired
	private VeiculoComInfracaoRepository veiculoComInfracaoRepository;
	
	
	@Autowired
	private ZonaRepository zonaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public Page<VeiculoComInfracaoDto> listar(@RequestParam(required=false) String placa,
			@PageableDefault(sort="momentoDoAlerta",direction=Direction.DESC,
			page=0,size=10) Pageable paginacao){
		//Pageable paginacao = PageRequest.of(pagina, qtde);
		if(placa==null) {
			Page<VeiculoComInfracao> veiculosSuspeitos = veiculoComInfracaoRepository.findAll(paginacao);
			
			return VeiculoComInfracao.converter(veiculosSuspeitos);
		}else {
			Page<VeiculoComInfracao> veiculosSuspeitos = veiculoComInfracaoRepository.findByPlaca(placa,
					paginacao);
			
			return VeiculoComInfracao.converter(veiculosSuspeitos);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<VeiculoComInfracaoDto> retornarEspecifico(@PathVariable("id") Long id) {
		Optional<VeiculoComInfracao> veiculo = veiculoComInfracaoRepository.findById(id);
		if(veiculo.isPresent()) {
			return ResponseEntity.ok(VeiculoComInfracao.converter(veiculo.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<VeiculoComInfracaoDto> cadastrar(@RequestBody @Valid VeiculoComInfracaoForm form,
			UriComponentsBuilder uriBuilder ){
		VeiculoComInfracao veiculo = form.converter(zonaRepository,usuarioRepository);
		veiculoComInfracaoRepository.save(veiculo);
		
		URI uri = uriBuilder.path("/api/veiculos_com_infracao/{id}")
				.buildAndExpand(veiculo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VeiculoComInfracaoDto(veiculo));
	}

	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VeiculoComInfracaoDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid VeiculoComInfracaoForm form){
		Optional<VeiculoComInfracao> veiculoOpt = veiculoComInfracaoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			VeiculoComInfracao veiculo = form.atualizar(id,veiculoComInfracaoRepository,
					zonaRepository, usuarioRepository);
			return ResponseEntity.ok(VeiculoComInfracao.converter(veiculo));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		Optional<VeiculoComInfracao> veiculoOpt=veiculoComInfracaoRepository.findById(id);
		
		if(veiculoOpt.isPresent()) {
			veiculoComInfracaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
