package dev.facturador;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Inicia Spring y con la anotacion tambien indica que los subPaquetes a este tiene que escanearlos
 * en busca de clases para agregar al contexto
 */
@SpringBootApplication
public class SpringbootApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	/**
	 * Main para iniciar Spring
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
