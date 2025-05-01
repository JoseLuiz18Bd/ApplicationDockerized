package br.com.book_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition(info =
        @Info(title = "Book Service API",
                version = "V1",
                description = "Documentation of Book Service API"
        )
)
public class OpenApiConfiguration {

    @Bean
    OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(
					new io.swagger.v3.oas.models.info.Info()
					.title("Book Service API")
					.version("V1")
					.license(
						new License()
							.name("OpenAPI Swagger")
							.url("http://springdoc.org")
					)
				);
	}
}
