package com.sani.World.Banking.App;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "World Banking Application",
				description = "Backend REST APIs for World Banking Application",
				version = "v.1.0",
				contact = @Contact(
						name = "Sani Dogo",
						email = "Sani.jdogo@gmail.com",
						url = "https://github.com/CaptainSani"
				),
				license = @License(
						name = "World Banking Application",
						url = "https://github.com/CaptainSani"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Backend REST APIs for World Banking Application",
				url = "https://github.com/CaptainSani"
		)
)
public class WorldBankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorldBankingAppApplication.class, args);
	}

}
