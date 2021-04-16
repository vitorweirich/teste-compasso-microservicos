package br.com.compasso.cliente.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<List<Cliente>> findByNomeContainingIgnoreCase(String nome);

}
