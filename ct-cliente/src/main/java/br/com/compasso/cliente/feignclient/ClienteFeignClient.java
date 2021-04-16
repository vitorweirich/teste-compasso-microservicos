package br.com.compasso.cliente.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.compasso.cliente.dto.CidadeDto;

@Component
@FeignClient(name = "ct-cidade", path = "/cidade")
public interface ClienteFeignClient {

	
	@GetMapping("/nome")
	ResponseEntity<CidadeDto> buscaCidadePeloNome(@RequestParam String nome);
	
}
