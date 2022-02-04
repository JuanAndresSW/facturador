package dev.facturador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Inicia Spring y con la anotacion tambien indica que los subPaquetes a este tiene que escanearlos
 * en busca de clases para agregar al contexto
 */
@SpringBootApplication
public class SpringbootApplication {
	/**
	 * Main para iniciar Spring
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
