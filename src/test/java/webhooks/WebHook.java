package webhooks;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;

public class WebHook {

    @BeforeEach
    public void beforeEach(){
        RestAssured.filters(new ResponseLoggingFilter(), new RequestLoggingFilter());
    }
}
