package br.com.compasso.cliente.controller.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import br.com.compasso.cliente.config.CidadeException;
import br.com.compasso.cliente.dto.CidadeDto;
import br.com.compasso.cliente.dto.service.ClienteService;
import br.com.compasso.cliente.model.Cliente;

public class ClienteForm {

	private static DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@NotEmpty
	@NotNull
	private String nome;
	@NotEmpty
	@NotNull
	private String sexo; 
	@NotEmpty
	@NotNull
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	private String dataNascimento;
	@NotNull @Positive
	private Integer idade;
	@NotEmpty
	@NotNull
	private String cidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Cliente converter(ClienteService service) {
		Cliente cliente = new Cliente();
		CidadeDto cidade = service.findByNomeIgnoreCase(this.cidade);
		if (cidade == null) {
			System.out.println("--------------------------------- Cidade Null No Form ------------------------");
			throw new CidadeException(this.cidade + " não foi encontrada no banco de dados");
		}
		cliente.setCidade(cidade.getNome());
		cliente.setNome(this.nome);
		cliente.setIdade(this.idade);
		cliente.setSexo(this.sexo);
		cliente.setDataNascimento(LocalDate.parse(this.dataNascimento, formater));
		return cliente;
	}

}
