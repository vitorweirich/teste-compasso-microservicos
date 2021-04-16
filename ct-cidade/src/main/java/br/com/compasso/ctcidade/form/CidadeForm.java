package br.com.compasso.ctcidade.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.ctcidade.model.Cidade;

public class CidadeForm {

	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cidade converter() {
		Cidade cidade = new Cidade();
		cidade.setEstado(this.estado);
		cidade.setNome(this.nome);
		return cidade;
	}

}
