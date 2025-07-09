package pcy.study.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirstApiPrivateFilter extends AbstractGatewayFilterFactory<FirstApiPrivateFilter.Config> {

    public FirstApiPrivateFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("FirstApiPrivateFilter URI: {}", exchange.getRequest().getURI());
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}
