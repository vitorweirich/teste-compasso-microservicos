package br.com.compasso.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.compasso.user.fignclient.UserFeignClient;
import br.com.compasso.user.model.User;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserFeignClient userFeignClient;
	
	public User findByEmail(String email) {
		ResponseEntity<User> findByEmail = userFeignClient.findByEmail(email);
		if (findByEmail.getBody() == null){
			System.out.println("------- Email not found: "+ findByEmail.getBody().getEmail());
			throw new IllegalArgumentException("Email not found");
		}
		System.out.println("------- Email found: "+ findByEmail.getBody().getEmail());
		return findByEmail.getBody();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResponseEntity<User> findByEmail = userFeignClient.findByEmail(username);
		if (findByEmail.getBody() == null){
			System.out.println("------- Email not found: "+ findByEmail.getBody().getEmail());
			throw new UsernameNotFoundException("Email not found");
		}
		System.out.println("------- Email found: "+ findByEmail.getBody().getEmail());
		return findByEmail.getBody();
	}
}
