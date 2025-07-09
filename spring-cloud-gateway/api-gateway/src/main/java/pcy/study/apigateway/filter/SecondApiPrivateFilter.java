package pcy.study.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SecondApiPrivateFilter extends AbstractGatewayFilterFactory<SecondApiPrivateFilter.Config> {

    public SecondApiPrivateFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("SecondApiPrivateFilter URI: {}", exchange.getRequest().getURI());
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}
