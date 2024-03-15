package tests;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RickMortyTest {

    @Test
    public void getCharacter() {
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://rickandmortyapi.com/api/character/2")
                .then()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> episodes = jsonPath.getList("episode");
        String speciesRick = jsonPath.getString("species");
        String locationRick = jsonPath.getString("location.name");
        String lastEpisodeRickLink = episodes.get(episodes.size() - 1);

        Response response2 = given()
                .when()
                .contentType(ContentType.JSON)
                .get(lastEpisodeRickLink)
                .then()
                .extract().response();
        JsonPath jsonPath2 = response2.jsonPath();
        List<String> charInEpisode = jsonPath2.getList("characters");
        String lastCharLink = charInEpisode.get(charInEpisode.size() - 1);

        Response response3 = given()
                .when()
                .contentType(ContentType.JSON)
                .get(lastCharLink)
                .then()
                .extract().response();

        JsonPath jsonPath3 = response3.jsonPath();
        String speciesLastChar = jsonPath3.getString("species");
        String locationLastChar = jsonPath3.getString("location.name");
        int a = 0;

        Assertions.assertEquals(speciesLastChar, speciesRick);
        Assertions.assertNotEquals(locationLastChar, locationRick);
    }

    @Test
    public void postTest() throws IOException {
        String bodyForCreate = new String(Files.readAllBytes(Paths.get("src/test/resources/param.json")));
        bodyForCreate = bodyForCreate.replace("Potato", "Tomato");
        JSONObject bodyForCreateJSON = new JSONObject(bodyForCreate);
        bodyForCreateJSON = bodyForCreateJSON.put("job", "Eat maket");
        bodyForCreate = bodyForCreateJSON.toString();
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(bodyForCreate)
                .post("https://reqres.in/api/users")
                .then().statusCode(201)
                .log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int c = 5;
    }
}
