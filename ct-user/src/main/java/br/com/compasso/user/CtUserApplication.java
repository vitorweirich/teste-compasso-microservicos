package br.com.compasso.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CtUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtUserApplication.class, args);
	}

}
