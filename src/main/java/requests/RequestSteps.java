package requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static specifications.Specifications.*;

public class RequestSteps {
    public static Response getRequest(String url, Integer statCode) {
        installSpecification(requestSpecification(), responseSpecification(statCode));
        return given()
                .when()
                .get(url)
                .then()
                .extract().response();
    }

    public static Response postRequest(String url, String path, Integer statCode, String body) {
        installSpecification(requestSpecification(url), responseSpecification(statCode));
        return given()
                .when()
                .body(body)
                .post(path)
                .then().statusCode(201)
                .extract().response();
    }
}
