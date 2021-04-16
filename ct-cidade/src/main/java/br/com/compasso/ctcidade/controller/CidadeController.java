package br.com.compasso.ctcidade.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.ctcidade.dto.CidadeDto;
import br.com.compasso.ctcidade.form.CidadeForm;
import br.com.compasso.ctcidade.model.Cidade;
import br.com.compasso.ctcidade.repository.CidadeRepository;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping("/nome")
	@Transactional
	public ResponseEntity<CidadeDto> buscaCidadePeloNome(@RequestParam String nome) {
		Optional<Cidade> cidade = cidadeRepository.findByNomeIgnoreCase(nome);
		if (cidade.isPresent())
			return ResponseEntity.ok(new CidadeDto(cidade.get()));

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/estado")
	@Transactional
	public ResponseEntity<List<CidadeDto>> buscaCidadePeloEstado(@RequestParam String estado) {
		Optional<List<Cidade>> cidades = cidadeRepository.findByEstadoContainingIgnoreCase(estado);
		if ((cidades.isPresent()) && (!cidades.get().isEmpty()))
			return ResponseEntity.ok(CidadeDto.converter(cidades.get()));

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CidadeDto> cadastrar(@RequestBody @Valid CidadeForm cidadeForm, UriComponentsBuilder uriBuilder) {
		Cidade cidade = cidadeForm.converter();
		cidadeRepository.save(cidade);
		
		URI uri = uriBuilder.path("/cidade/nome?nome={nome}").buildAndExpand(cidade.getNome()).toUri();
	
		return ResponseEntity.created(uri).body(new CidadeDto(cidade));
	}
}