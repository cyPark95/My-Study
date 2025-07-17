package pcy.study.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pcy.study.gateway.filter.SecondApiPrivateFilter;
import pcy.study.gateway.filter.SecondApiPublicFilter;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {

    private final SecondApiPublicFilter secondApiPublicFilter;
    private final SecondApiPrivateFilter secondApiPrivateFilter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/gateway/second/public/**")
                        .filters(gatewayFilterSpec -> {
                            gatewayFilterSpec.rewritePath("/gateway(?<segment>.*)", "${segment}");
                            gatewayFilterSpec.filter(secondApiPublicFilter.apply(new SecondApiPublicFilter.Config()));
                            return gatewayFilterSpec;
                        })
                        .uri("http://localhost:8090"))
                .route(predicateSpec -> predicateSpec.path("/gateway/second/private/**")
                        .filters(gatewayFilterSpec -> {
                            gatewayFilterSpec.rewritePath("/gateway(?<segment>.*)", "${segment}");
                            gatewayFilterSpec.filter(secondApiPrivateFilter.apply(new SecondApiPrivateFilter.Config()));
                            return gatewayFilterSpec;
                        })
                        .uri("http://localhost:8090"))
                .build();
    }
}
