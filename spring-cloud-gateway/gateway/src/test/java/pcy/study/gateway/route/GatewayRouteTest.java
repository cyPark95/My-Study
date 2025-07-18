package pcy.study.gateway.route;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GatewayRouteTest {

    @Autowired
    private RouteLocator routeLocator;

    @DisplayName("Route 목록 검증")
    @Test
    void routeDefinitionsShouldContainExpectedIds() {
        List<String> expectedRouteIds = List.of("yml-config", "java-config", "account-api");

        Mono<List<String>> routes = routeLocator.getRoutes()
                .map(Route::getId)
                .collectList();

        StepVerifier.create(routes)
                .expectNextMatches(routeIds -> routeIds.containsAll(expectedRouteIds))
                .verifyComplete();
    }
}
