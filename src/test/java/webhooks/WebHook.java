package webhooks;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import pages.JiraAuthPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class WebHook {

    private static final String START_PAGE_URL = "https://edujira.ifellow.ru";
    private static final int TIME_FOR_LOAD_PAGE = 5000;
    private final JiraAuthPage pageAuth = new JiraAuthPage();

    @BeforeAll
    public static void setUp(){
        Selenide.open(START_PAGE_URL);
        Selenide.sleep(TIME_FOR_LOAD_PAGE);
        getWebDriver().manage().window().maximize();
    }

    @Before(value = "@doBug")
    public void openAuthPageTest(){
        if (pageAuth.checkAuth()) {
            pageAuth.doLogin();
            Selenide.sleep(TIME_FOR_LOAD_PAGE);
        }
    }

    @AfterAll
    public static void tearDown() {Selenide.closeWebDriver();}
}
