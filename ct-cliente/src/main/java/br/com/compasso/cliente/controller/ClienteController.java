package br.com.compasso.cliente.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.cliente.controller.form.ClienteAtualizarForm;
import br.com.compasso.cliente.controller.form.ClienteForm;
import br.com.compasso.cliente.dto.ClienteDto;
import br.com.compasso.cliente.dto.service.ClienteService;
import br.com.compasso.cliente.model.Cliente;
import br.com.compasso.cliente.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService service;

	@GetMapping("/nome")
	@Transactional
	public ResponseEntity<List<ClienteDto>> buscaClientePeloNome(@RequestParam String nome) {
		System.out.println("------------------ Entrou Aqui ---------------------");
		Optional<List<Cliente>> clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);
		if(clientes.isPresent() && !clientes.get().isEmpty()){
			return ResponseEntity.ok(ClienteDto.converter(clientes.get(), service));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/id")
	@Transactional
	public ResponseEntity<ClienteDto> buscaClientePeloId(@RequestParam String id) {
		Optional<Cliente> cliente = clienteRepository.findById(Long.parseLong(id));
		if (cliente.isPresent())
			return ResponseEntity.ok(new ClienteDto(cliente.get(), service));
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm clienteform, UriComponentsBuilder uriBuilder){
		System.out.println("------------------ Entrou Aqui ---------------------");
		Cliente cliente = clienteform.converter(service);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		clienteRepository.save(cliente);
		URI uri = uriBuilder.path("/cliente/id?id={id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ClienteDto(cliente, service));
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDto> atualizar(@PathVariable String id, @RequestBody @Valid ClienteAtualizarForm nomeForm){
		Optional<Cliente> findById = clienteRepository.findById(Long.parseLong(id));
		if(findById.isPresent()) {
			Cliente cliente = findById.get();
			cliente.setNome(nomeForm.getNome());
			return ResponseEntity.ok(new ClienteDto(cliente, service));
		}
		return ResponseEntity.notFound().build();
	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<Cliente> atualizar(@PathVariable String id, @RequestParam @Valid @NotEmpty @NotNull String nome){
//		Optional<Cliente> findById = clienteRepository.findById(Long.parseLong(id));
//		if(findById.isPresent()) {
//			Cliente cliente = findById.get();
//			cliente.setNome(nome);
//			clienteRepository.save(cliente);
//			return ResponseEntity.ok(cliente);
//		}
//		return ResponseEntity.notFound().build();
//	}
	
//	@PostMapping("")
//	@Transactional
//	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm clienteForm, UriComponentsBuilder uriBuilder) {
//		Cliente cliente = clienteForm.converter(clienteRepository);
//		clienteRepository.save(cliente);
//		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(cliente.getId()).toUri();
//		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
//	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDto> deletar(@PathVariable Long id){
		Optional<Cliente> optional = clienteRepository.findById(id);
		if(optional.isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok(new ClienteDto(optional.get(), service));//.build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
