package br.com.pti.muralha_inteligente.alertas_de_carros_suspeitos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class AlertasDeCarrosSuspeitosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertasDeCarrosSuspeitosApplication.class, args);
	}

}
