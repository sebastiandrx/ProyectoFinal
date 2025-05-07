package com.example.ProyectoFinal.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                    .title("API ProyectoFinal")
                    .version("1.0")
                    .description("Documentacion de la API del ProyectoFinal")
                    .contact(new Contact()
                            .name("Juan Dueñas")
                            .email("juan.duenas-r@uniminuto.edu.co")));

    }
}

//RUTA DE ACCESO: http://localhost:8080/swagger-ui/index.html
