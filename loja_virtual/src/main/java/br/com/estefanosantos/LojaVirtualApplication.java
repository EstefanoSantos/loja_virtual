package br.com.estefanosantos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "br.com.estefanosantos.model")
@ComponentScan(basePackages = {"br.*"})
@EnableJpaRepositories(basePackages = {"br.com.estefanosantos.repository"})
@EnableTransactionManagement
public class LojaVirtualApplication {

	public static void main(String[] args) {
		
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
		SpringApplication.run(LojaVirtualApplication.class, args);
	}

}
