package pcy.study.gateway.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class YmlRouteFilter extends AbstractGatewayFilterFactory<YmlRouteFilter.Config> {

    public YmlRouteFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("YML Route URI: {} / Param: {}", exchange.getRequest().getURI(), config.getParam());
            return chain.filter(exchange);
        };
    }

    @Getter
    @Setter
    public static class Config {

        private String param;
    }
}
