package webhooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.JiraAuthPage;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class WebHook {

    private static final String START_PAGE_URL = "https://edujira.ifellow.ru";
    private final JiraAuthPage pageAuth = new JiraAuthPage();



    @BeforeAll
    public static void setUp(){
        Configuration.baseUrl = "https://edujira.ifellow.ru";
        Configuration.timeout = 10000;
        Selenide.open("https://edujira.ifellow.ru");
        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
//        WebDriverRunner.setWebDriver(driver);
        System.out.println("AllBeforeAll");
//        Selenide.open(START_PAGE_URL);
//        driver.get(START_PAGE_URL);
    }


    @Before(value = "@doBug")
    public void openAuthPageTest(){
        System.out.println("Before");
        if (pageAuth.checkAuth()) {
            pageAuth.doLogin();
        }
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("after");
        Selenide.closeWebDriver();
    }
}

//    @AfterAll
//    public static void tearDown() {
//        Selenide.closeWebDriver();
//    }
//}
