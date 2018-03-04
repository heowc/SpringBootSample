package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		Contact contact = new Contact("Heo Won Chul",
				"https://heowc.github.io",
				"heowc1992@gmail.com");
		ApiInfo info = new ApiInfo(
				"Swagger for spring boot",
				"This documents describes about swagger for spring boot",
				"v1",
				"localhost:8080/",
				contact,
				"MIT",
				"https://opensource.org/licenses/MIT",
				new ArrayList<>());

		ArrayList<ResponseMessage> responseMessages = new ArrayList<>();
		responseMessages.add(new ResponseMessageBuilder()
				.code(500)
				.message("500 message")
				.build());

		responseMessages.add(new ResponseMessageBuilder()
				.code(404)
				.message("404 message")
				.build());

		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET, responseMessages)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/member/**"))
				.build()
				.apiInfo(info);
	}
}
