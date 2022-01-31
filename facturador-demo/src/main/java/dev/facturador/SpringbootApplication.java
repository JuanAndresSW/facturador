package dev.facturador;

import dev.facturador.exception.CustomExceptionHandlerResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerExceptionResolver;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public HandlerExceptionResolver customHandlerExceptionResolver(){
		return new CustomExceptionHandlerResolver();
	}
}
