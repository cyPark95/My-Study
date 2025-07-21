package pcy.study.springcloudgateway.route;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AccountRouteTest {

    @Autowired
    private WebTestClient webTestClient;

    private WireMockServer mockApiServer;
    private WireMockServer mockAccountServer;

    @BeforeEach
    void setUp() {
        this.mockAccountServer = new WireMockServer(WireMockConfiguration.options().port(8090));
        this.mockApiServer = new WireMockServer(WireMockConfiguration.options().port(8080));

        mockAccountServer.start();
        mockApiServer.start();
    }

    @AfterEach
    void tearDown() {
        mockApiServer.stop();
        mockAccountServer.stop();
    }

    @DisplayName("유효한 인증 토큰일 때, API 서비스로 정상 라우팅 된다")
    @Test
    void routeToServiceAfterAuthentication() {
        // given
        String token = "AccessToken";
        String username = "pcy";
        String response = String.format("Received username: %s", username);

        mockAccountServer.stubFor(post("/username")
                .withRequestBody(matchingJsonPath("$.token", containing(token)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(username)));

        mockApiServer.stubFor(post("/api/account")
                .withHeader("x-username", equalTo(username))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));

        // when
        // then
        webTestClient.post()
                .uri("/gateway/api/account")
                .header("Authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(body -> assertThat(body).isEqualTo(response));

        // then
        mockAccountServer.verify(postRequestedFor(urlEqualTo("/username")));
        mockApiServer.verify(postRequestedFor(urlEqualTo("/api/account")));
    }

    @DisplayName("유효하지 않은 인증 토큰인 경우, 인증 실패 예외를 반환한다")
    @Test
    void failureAuthentication() {
        // given
        String invalidToken = "Invalid Token";
        String response = "Unauthorized Access";

        mockAccountServer.stubFor(post("/username")
                .withRequestBody(matchingJsonPath("$.token", containing(invalidToken)))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.UNAUTHORIZED.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));

        // when
        // then
        webTestClient.post()
                .uri("/gateway/api/account")
                .header("Authorization", invalidToken)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(String.class)
                .value(body -> assertThat(body).contains(response));

        // then
        mockAccountServer.verify(postRequestedFor(urlEqualTo("/username")));
    }
}
