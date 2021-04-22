package br.com.compasso.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
