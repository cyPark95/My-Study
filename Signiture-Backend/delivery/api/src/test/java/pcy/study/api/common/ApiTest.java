package pcy.study.api.common;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.api.domain.token.business.TokenBusiness;
import pcy.study.db.user.UserRepository;

import static pcy.study.api.utility.UserUtils.USER_ID;
import static pcy.study.api.utility.UserUtils.createUser;

public abstract class ApiTest extends OpenApiTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenBusiness tokenBusiness;

    private String token;

    @BeforeEach
    void init() {
        super.init();
        token = createToken();
    }

    private String createToken() {
        userRepository.save(createUser());
        return tokenBusiness.issueToken(USER_ID).accessToken();
    }

    @Override
    protected ExtractableResponse<Response> get(String url) {
        return RestAssured
                .given().log().all()
                .header("authorization-token", token)
                .when()
                .get(url)
                .then().log().all()
                .extract();
    }

    @Override
    protected ExtractableResponse<Response> post(Object data, String url) {
        return RestAssured
                .given().log().all()
                .header("authorization-token", token)
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post(url)
                .then().log().all()
                .extract();
    }

    @Override
    protected ExtractableResponse<Response> put(Object data, String url) {
        return RestAssured
                .given().log().all()
                .header("authorization-token", token)
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .put(url)
                .then().log().all()
                .extract();
    }

    @Override
    protected ExtractableResponse<Response> delete(String url) {
        return RestAssured
                .given().log().all()
                .header("authorization-token", token)
                .when()
                .delete(url)
                .then().log().all()
                .extract();
    }
}
