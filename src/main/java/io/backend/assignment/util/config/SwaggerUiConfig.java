package io.backend.assignment.util.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerUiConfig {

    @Bean
    public GroupedOpenApi productApi() {
        return GroupedOpenApi.builder().group("product").pathsToMatch("/api/v1/products/**").build();
    }

    @Bean
    public GroupedOpenApi orderApi() {
        return GroupedOpenApi.builder().group("order").pathsToMatch("/api/v1/orders/**").build();
    }

    @Bean
    public GroupedOpenApi customerApi() {
        return GroupedOpenApi.builder().group("customer").pathsToMatch("/api/v1/customers/**").build();
    }

    @Bean
    public GroupedOpenApi cartApi() {
        return GroupedOpenApi.builder().group("cart").pathsToMatch("/api/v1/carts/**").build();
    }
}
