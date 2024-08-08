package com.nisum.apirest;

import com.nisum.apirest.constants.AppConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NisumApiRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(NisumApiRestApplication.class, args);
    }

    @Bean
    public OpenAPI getOpenApiDocumentation(){
        return new OpenAPI().info(new Info().title(AppConstants.API_REST_TITLE).
                version(AppConstants.API_REST_VERSION));
    }

}
