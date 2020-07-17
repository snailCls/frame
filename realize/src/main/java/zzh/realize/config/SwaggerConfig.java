package zzh.realize.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author snail
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private Boolean swaggerOff = true;

    @Bean
    public Docket newApi() {
        if(swaggerOff) {
            Docket docket = new Docket(DocumentationType.SWAGGER_2);
            docket.enable(true);
            docket.apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(""))
                    .paths(PathSelectors.any())
                    .build();
            return docket;
        }else {
            Docket docket = new Docket(DocumentationType.SWAGGER_2);
            docket.enable(false);
            return docket;
        }
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api文档")
                .description("restfun 风格接口")
                //服务条款网址
//                .termsOfServiceUrl("http://blog.csdn.net/forezp")
                .version("1.0")
                .build();
    }

}
