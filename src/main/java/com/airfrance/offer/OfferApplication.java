package com.airfrance.offer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OfferApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfferApplication.class, args);
    }

    /**
     * @param appDesciption
     * @param appVersion
     * @return OpenAPI
     * @apiNote generate Swagger documentation
     */
    @Bean
    public OpenAPI customOpenAPI(@Value("${spring.application.description}") String appDesciption, @Value("${spring.application.version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("sample application API")
                        .version(appVersion)
                        .description(appDesciption)
                        .version("1.0")
                        .contact(
                                new Contact()
                                        .name("tahar")
                                        .email("taharkerdoud@gmail.com")
                                        .url("cat-amania.com"))
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
