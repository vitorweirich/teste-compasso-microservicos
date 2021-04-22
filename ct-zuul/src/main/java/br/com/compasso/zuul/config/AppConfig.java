package br.com.compasso.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {
	
	private final String jwtSecret = "compasso-teste-app-secret";
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwt = new JwtAccessTokenConverter();
		jwt.setSigningKey(jwtSecret);
		return jwt;
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		JwtTokenStore jwt = new JwtTokenStore(jwtAccessTokenConverter());
		return jwt;
	}
}
