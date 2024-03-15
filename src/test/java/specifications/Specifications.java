package specifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecification(Integer statusResponse){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusResponse)
                .build();
    }

    public static void installSpecification (RequestSpecification requestSpec, ResponseSpecification responseSpec){
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }
}
