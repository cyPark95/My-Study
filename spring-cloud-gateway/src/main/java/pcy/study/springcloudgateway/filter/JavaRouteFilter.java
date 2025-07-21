package pcy.study.springcloudgateway.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JavaRouteFilter extends AbstractGatewayFilterFactory<JavaRouteFilter.Config> {

    public JavaRouteFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("Java Route URI: {} / Param: {}", exchange.getRequest().getURI(), config.getParam());
            return chain.filter(exchange);
        };
    }

    @Getter
    @Setter
    public static class Config {

        private String param;
    }
}
