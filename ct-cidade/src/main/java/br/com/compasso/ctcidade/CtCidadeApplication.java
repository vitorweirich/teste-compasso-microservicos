package br.com.compasso.ctcidade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CtCidadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtCidadeApplication.class, args);
	}

}
