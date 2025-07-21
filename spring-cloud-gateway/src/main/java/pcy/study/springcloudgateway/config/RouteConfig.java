package pcy.study.springcloudgateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import pcy.study.springcloudgateway.filter.AccountApiFilter;
import pcy.study.springcloudgateway.filter.JavaRouteFilter;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {

    private final JavaRouteFilter javaRouteFilter;
    private final AccountApiFilter accountApiFilter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("java-config", predicate -> predicate.path("/gateway/api/java/**")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(filter -> {
                            filter.rewritePath("/gateway(?<segment>.*)", "${segment}");

                            JavaRouteFilter.Config config = new JavaRouteFilter.Config();
                            config.setParam("Java Config");
                            filter.filter(javaRouteFilter.apply(config));

                            return filter;
                        })
                        .uri("http://localhost:8080")
                )
                .route("account-api", predicate -> predicate.path("/gateway/api/account/**")
                        .and()
                        .method(HttpMethod.POST)
                        .filters(filter -> {
                            filter.rewritePath("/gateway(?<segment>.*)", "${segment}");
                            filter.filter(accountApiFilter.apply(new AccountApiFilter.Config()));
                            return filter;
                        })
                        .uri("http://localhost:8080")
                )
                .build();
    }
}
