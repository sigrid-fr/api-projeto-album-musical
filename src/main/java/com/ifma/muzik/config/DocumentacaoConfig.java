package com.ifma.muzik.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class DocumentacaoConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)

				.select().apis(RequestHandlerSelectors.basePackage("com.ifma.albumManager.controller"))
				.paths(PathSelectors.any()).build().apiInfo(this.informacoesDaAPI());
	}

	private ApiInfo informacoesDaAPI() {
		return new ApiInfo("API REST - Sistema Gerenciador de Albuns Musicais-Muzik",
				"API desenvolvida com Spring Boot, Spring Web e Spring REST.", "1.0", "Termo de Serviço",
				new Contact("Sigrid", "www.example.com", "sigferodrigues@gmail.com"), "Copyright Muzik",
				"API license URL", Collections.emptyList());

	}

}

