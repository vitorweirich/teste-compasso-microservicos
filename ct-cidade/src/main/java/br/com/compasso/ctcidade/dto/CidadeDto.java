package br.com.compasso.ctcidade.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.compasso.ctcidade.model.Cidade;

public class CidadeDto {

	//private Long id;
	private String nome;
	private String estado;

	public CidadeDto(Cidade cidade) {
		//this.id = cidade.getId();
		this.nome = cidade.getNome();
		this.estado = cidade.getEstado();
	}

//	public Long getId() {
//		return id;
//	}

	public String getNome() {
		return nome;
	}

	public String getEstado() {
		return estado;
	}

	public static List<CidadeDto> converter(List<Cidade> list) {
		List<CidadeDto> cidades = new ArrayList<CidadeDto>();
		for (Cidade cidade : list) {
			cidades.add(new CidadeDto(cidade));
		}
		return cidades;
	}

}
