package br.com.compasso.cliente.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.compasso.cliente.dto.service.ClienteService;
import br.com.compasso.cliente.model.Cliente;

public class ClienteDto {

	private Long id;
	private String nome;
	private String sexo;
	private LocalDate dataNascimento;
	private Integer idade;
	private CidadeDto cidade;

	public ClienteDto(Cliente cliente, ClienteService service) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.sexo = cliente.getSexo();
		this.dataNascimento = cliente.getDataNascimento();
		this.idade = cliente.getIdade();
		this.cidade = service.findByNomeIgnoreCase(cliente.getCidade());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSexo() {
		return sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Integer getIdade() {
		return idade;
	}

	public CidadeDto getCidade() {
		return cidade;
	}

	public static List<ClienteDto> converter(List<Cliente> list, ClienteService service) {
		List<ClienteDto> clientes = new ArrayList<ClienteDto>();
		for (Cliente cliente : list) {
			ClienteDto aux = new ClienteDto(cliente, service); 
			clientes.add(aux);
		}
		return clientes;
	}

}
