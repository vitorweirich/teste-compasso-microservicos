package br.com.compasso.cliente.dto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.compasso.cliente.dto.CidadeDto;
import br.com.compasso.cliente.feignclient.ClienteFeignClient;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteFeignClient feignclient;
	
	public CidadeDto findByNomeIgnoreCase(String cidade) {
		ResponseEntity<CidadeDto> buscaCidadePeloNome = feignclient.buscaCidadePeloNome(cidade);
		if (!buscaCidadePeloNome.hasBody()) {
			System.out.println("--------------------------------- Cidade Null No Service ------------------------");
			return null;
		}
		return buscaCidadePeloNome.getBody();
	}

}
