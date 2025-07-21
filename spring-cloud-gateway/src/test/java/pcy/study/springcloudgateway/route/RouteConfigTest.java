package pcy.study.springcloudgateway.route;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(stubs = "classpath:mappings/config")
class RouteConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private WireMockServer wireMockServer;

    @DisplayName("YML 설정으로 라우팅 된다")
    @Test
    void routeToYmlConfig() {
        webTestClient.get()
                .uri("/gateway/api/yml")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(body -> assertThat(body).isEqualTo("Success: Routed using YML configuration"));

        wireMockServer.verify(getRequestedFor(urlEqualTo("/api/yml")));
    }

    @DisplayName("Java 설정으로 라우팅 된다")
    @Test
    void routeToJavaConfig() {
        webTestClient.get()
                .uri("/gateway/api/java")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(body -> assertThat(body).isEqualTo("Success: Routed using Java configuration"));

        wireMockServer.verify(getRequestedFor(urlEqualTo("/api/java")));
    }
}
