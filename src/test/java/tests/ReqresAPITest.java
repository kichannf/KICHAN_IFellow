package tests;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webhooks.WebHook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static steps.RequestSteps.postRequest;

public class ReqresAPITest extends WebHook {
    private static final String URL = "https://reqres.in";
    private static final String pathCreate = "/api/users";
    private static final String pathParamJSON = "src/test/resources/param.json";


    @DisplayName("Post запрос Create")
    @Test
    public void postCreate() throws IOException {
        String body = new String(
                Files.readAllBytes(Paths.get(pathParamJSON))
                ).replace("Potato", "Tomato");
        JSONObject bodyJSON = new JSONObject(body).put("job", "Eat maket");
        JsonPath jsonPath = postRequest(URL, pathCreate, 201, bodyJSON.toString()).jsonPath();
        Assertions.assertEquals(jsonPath.getString("name"), bodyJSON.getString("name"));
        Assertions.assertEquals(jsonPath.getString("job"), bodyJSON.getString("job"));
    }
}
