package br.com.compasso.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.user.model.User;
import br.com.compasso.user.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		Optional<User> findById = userRepository.findById(id);
		return ResponseEntity.ok(findById.get());
	}
	
	@GetMapping("/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email){
		User findByEmail = userRepository.findByEmail(email);
		return ResponseEntity.ok(findByEmail);
	}
}
