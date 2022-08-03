package com.project.Batnik.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private io.swagger.v3.oas.models.info.Info apiInfo() {
        return new Info()
                .title("Upgraded to do list API")
                .description("API for creating projects and tasks for more comfortable planning and execution of tasks")
                .version("2.0")
                .contact(apiContact());
    }

    private io.swagger.v3.oas.models.info.Contact apiContact() {
        return new Contact()
                .name("Vladislav")
                .email("vlad.snigur.2000@gmail.com")
                .url("https://github.com/Or1gn/upgraded-to-do-list");
    }
}
