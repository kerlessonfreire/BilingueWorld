package br.com.projeto.BelingueWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages= {"br.*"})
@SpringBootApplication
public class BelingueWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelingueWorldApplication.class, args);
	}

}
