package webhooks;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.cucumber.java.Before;
import util.SetProperties;

import java.io.IOException;

public class WebHook {
    @Before
    public void before() throws IOException {
        RestAssured.filters(new ResponseLoggingFilter(), new RequestLoggingFilter());
        SetProperties.setProperties();
    }
}
