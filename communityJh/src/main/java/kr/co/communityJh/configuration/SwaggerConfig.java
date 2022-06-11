package kr.co.communityJh.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jhlee
 * Swagger config
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		ApiInfoBuilder apiBuilder = new ApiInfoBuilder();
		apiBuilder.title("API 문서");
		apiBuilder.description("API 문서입니다.");
		
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(apiBuilder.build());
		
		ApiSelectorBuilder apis = docket.select().apis(RequestHandlerSelectors.basePackage("kr.co.communityJh.controller"));
		apis.paths(PathSelectors.ant("/**"));
		
		return apis.build();
	}
}
