package com.example.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}


	@Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            @Value("${gateway.host}") String host,
            @Value("${company.url}") String companyUrl,
            @Value("${employee.url}") String employeeUrl
    ){
        return builder.routes()
                .route("company", r -> r
                        .host(host)
                        .and()
                        .path(
                                "/companies",
                                "/companies/{uuid}"
                        ).uri(companyUrl)
                )
                .route("partner", r -> r
                        .host(host)
                        .and()
                        .path(
                                "/employees",
                                "/employees/{uuid}",
								"/companies/{uuid}/employees"
						).uri(employeeUrl)
                )
                .build();
    }

}
