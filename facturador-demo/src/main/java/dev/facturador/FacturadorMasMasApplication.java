package dev.facturador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class FacturadorMasMasApplication {

	public static void main(String... args) {
		SpringApplication.run(FacturadorMasMasApplication.class, args);
	}

	@Bean
	public WebClient webClient(){
		return WebClient.builder().baseUrl("http://localhost:8080").build();
	}
}
