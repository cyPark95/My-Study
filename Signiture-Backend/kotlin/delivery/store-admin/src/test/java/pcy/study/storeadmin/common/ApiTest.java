package pcy.study.storeadmin.common;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.db.storeuser.StoreUser;
import pcy.study.db.storeuser.StoreUserRepository;

import static pcy.study.storeadmin.utility.StoreUserUtils.*;

public abstract class ApiTest extends OpenApiTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StoreUserRepository userRepository;

    @BeforeEach
    void init() {
        super.init();
        saveStoreUser();
    }

    private void saveStoreUser() {
        StoreUser storeUser = createStoreUser();
        ReflectionTestUtils.setField(storeUser, "password", passwordEncoder.encode(STORE_USER_PASSWORD));
        userRepository.save(storeUser);
    }

    @Override
    protected ExtractableResponse<Response> get(String url) {
        return RestAssured
                .given().log().all()
                .auth().form(STORE_USER_EMAIL, STORE_USER_PASSWORD)
                .when()
                .get(url)
                .then().log().all()
                .extract();
    }

    @Override
    protected ExtractableResponse<Response> post(Object data, String url) {
        return RestAssured
                .given().log().all()
                .auth().form(STORE_USER_EMAIL, STORE_USER_PASSWORD)
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
                .auth().form(STORE_USER_EMAIL, STORE_USER_PASSWORD)
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
                .auth().form(STORE_USER_EMAIL, STORE_USER_PASSWORD)
                .when()
                .delete(url)
                .then().log().all()
                .extract();
    }
}
