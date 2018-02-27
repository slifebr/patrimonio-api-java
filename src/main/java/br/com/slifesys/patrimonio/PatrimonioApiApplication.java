package br.com.slifesys.patrimonio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.slifesys.patrimonio.config.property.PatrimonioApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(PatrimonioApiProperty.class)
public class PatrimonioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatrimonioApiApplication.class, args);
	}
}
