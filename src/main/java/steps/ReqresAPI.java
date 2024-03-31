package steps;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.System.getProperty;
import static requests.RequestSteps.postRequest;

public class ReqresAPI {
    private JSONObject bodyJSON;
    private JsonPath jsonPath;

    @Если("поменять name и добавить поле job в json файл")
    public void change() throws IOException {
        String body = new String(
                Files.readAllBytes(Paths.get(getProperty("reqres.path.json")))
        ).replace("Potato", "Tomato");
        bodyJSON = new JSONObject(body).put("job", "Eat maket");
    }

    @И("сделать запрос Create на создание пользователя")
    public void post(){
        jsonPath = postRequest(
                getProperty("reqres.url"),
                getProperty("reqres.path.users"),
                201, bodyJSON.toString()).jsonPath();
    }

    @Тогда("поля name и job в теле запроса и ответа равны")
    public void test(){
        Assertions.assertEquals(jsonPath.getString("name"), bodyJSON.getString("name"));
        Assertions.assertEquals(jsonPath.getString("job"), bodyJSON.getString("job"));
    }
}
