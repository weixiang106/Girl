package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi(){
     return new Docket(DocumentationType.SWAGGER_2)
             .apiInfo(apiInfo())
             .select()
             .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
             .paths(PathSelectors.any())
             .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API接口")
                .description("URL: http://192.168.3.44:8081/")
                .termsOfServiceUrl("http://www.baidu.com")
                .contact("weixiang106@163.com")
                .version("2.0")
                .build();
    }

}
