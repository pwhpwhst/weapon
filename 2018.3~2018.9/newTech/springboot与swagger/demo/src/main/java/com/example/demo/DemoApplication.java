package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.enable(true);
		docket.apiInfo(apiInfo()).select().paths(PathSelectors.any()).build();
		return docket;
	}
 
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder().title("livechat接口测试平台")//设置接口文档的标题
				.description("live-chat 项目web api接口规范：")
				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
	             .contact("lizhenjuan")//创建人
				 .license("China Red Star Licence Version 1.0")//版本号，点击进去是项目的地址
	             .licenseUrl("#")
				.version("1.0")
				.build();
	}

}




