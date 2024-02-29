package pcy.study.api.domain.user;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import pcy.study.api.config.db.DatabaseClearExtension;

@ExtendWith(DatabaseClearExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class OpenApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void init() {
        RestAssured.port = port;
    }

    protected ExtractableResponse<Response> get(String url) {
        return RestAssured
                .given().log().all()
                .when()
                .get(url)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> post(Object data, String url) {
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post(url)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> put(Object data, String url) {
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .put(url)
                .then().log().all()
                .extract();
    }

    protected ExtractableResponse<Response> delete(String url) {
        return RestAssured
                .given().log().all()
                .when()
                .delete(url)
                .then().log().all()
                .extract();
    }
}
