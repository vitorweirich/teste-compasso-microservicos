package br.com.compasso.ctcidade.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.ctcidade.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	Optional<Cidade> findByNomeIgnoreCase(String nome);

	Optional<List<Cidade>> findByEstadoContainingIgnoreCase(String estado);

}
