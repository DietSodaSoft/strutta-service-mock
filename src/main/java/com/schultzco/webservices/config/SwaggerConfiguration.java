package com.schultzco.webservices.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.*;

import static springfox.documentation.builders.PathSelectors.ant;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {


    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("full-api")
                .apiInfo(apiInfo())
                .select().paths(paths()).build()
                .pathMapping("/")

                // TODO: not sure we need this stuff
                .genericModelSubstitutes(ResponseEntity.class)
                .directModelSubstitute(MonthDay.class, String.class)
                .directModelSubstitute(YearMonth.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(ZonedDateTime.class, String.class)
                .directModelSubstitute(ZoneId.class, String.class)
                ;
    }

    private Predicate<String> paths() {
        return ant("/api/v1/**");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Application Web Services",
                "Application Web Services",
                "v1",
                "http://www.schultzco.com",  // todo: terms of service url
                "donotreply@schultzco.com",
                "MIT", "http://opensource.org/licenses/MIT"
        );
    }

}
