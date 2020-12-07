package com.martins.comunicacao.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Value("${swagger.titulo}")
  private String titulo;

  @Value("${swagger.nome.desenvolvedor}")
  private String nome;

  @Value("${swagger.linkedin.desenvolvedor}")
  private String linkedin;

  @Value("${swagger.email.desenvolvedor}")
  private String email;

  @Value("${swagger.descricao}")
  private String descricao;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/api/**"))
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .description(descricao)
        .title(titulo)
        .contact(new Contact(nome, linkedin, email))
        .build();
  }
}
