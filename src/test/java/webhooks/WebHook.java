package webhooks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.JiraAuthPage;

import java.io.IOException;
import java.time.Duration;

/**Установка и настройка вебдрайвера, подключение properties, настройка AllureSelenide*/
abstract public class WebHook {
    @BeforeAll
    public static void setUp() throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverRunner.setWebDriver(driver);
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        Selenide.open(System.getProperty("url"));
        JiraAuthPage.doLogin(System.getProperty("login"), System.getProperty("password"));
        SelenideLogger.addListener(
                "AllureSelenide",
                    new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(false)
        );
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}
