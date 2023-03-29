package com.mitraecp.api.mitratests.feature.login;
import com.mitraecp.api.mitratests.utils.Constants;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Objects;

import static io.restassured.RestAssured.given;

 public class FeatureLoginTest {

    @Test
    public void LoginTestWithSuccess(){
        ExtractableResponse<Response> response = extractableResponse();
        Assertions.assertEquals(response.statusCode(), HttpStatus.SC_OK);
        Assertions.assertNotNull(response.asString());
    }

    @Test
    public void LoginTestWithError(){

        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("laura.pontes@mitraecp.com");
        userLogin.setPassword("mitra123");

        given()
                .contentType("application/json")
                .body(userLogin)
                .when().post(Constants.LOGIN_PATH)
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }

    public static String getTokenLogin () {
        String contextToken = System.getProperty("loginToken");

        if(Objects.isNull(contextToken)) {
            String token = extractableResponse().asString();
            System.setProperty("loginToken", token);
            return  token;
        }
        return contextToken;
    }

    private static ExtractableResponse<Response> extractableResponse() {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("admin");
        userLogin.setPassword("mitra123");

        return given()
                .contentType("application/json")
                .body(userLogin)
                .when()
                .post(Constants.LOGIN_PATH)
                .then()
                .extract();
    }
}
