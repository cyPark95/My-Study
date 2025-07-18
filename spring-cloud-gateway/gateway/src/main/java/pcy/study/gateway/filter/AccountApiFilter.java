package pcy.study.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import pcy.study.gateway.exception.AuthenticationFailedException;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@Component
public class AccountApiFilter extends AbstractGatewayFilterFactory<AccountApiFilter.Config> {

    private final WebClient webClient;

    public AccountApiFilter() {
        super(Config.class);
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8090")
                .build();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("Account API Filter URI: {}", exchange.getRequest().getURI());

            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            log.info("Authorization Token: {}", token);

            Map<String, String> body = Map.of("token", token);

            return webClient.post()
                    .uri("/username")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(String.class)
                            .flatMap(error -> {
                                log.error("Token extraction failed: {}", error);
                                return Mono.error(new AuthenticationFailedException(error));
                            })
                    )
                    .bodyToMono(String.class)
                    .doOnNext(username -> log.info("Extracted username: {}", username))
                    .flatMap(username -> {
                        ServerHttpRequest mutatedRequest = exchange.getRequest()
                                .mutate()
                                .header("x-username", username)
                                .build();

                        ServerWebExchange mutatedExchange = exchange
                                .mutate()
                                .request(mutatedRequest)
                                .build();

                        return chain.filter(mutatedExchange);
                    })
                    .doOnError(error -> log.error("Error during token extraction: {}", error.getMessage()));
        };
    }

    public static class Config {
    }
}
