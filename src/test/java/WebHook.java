import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


abstract public class WebHook {

    private static final String START_PAGE_URL = "https://edujira.ifellow.ru";

    @BeforeAll
    public static void setUp(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        Selenide.open(START_PAGE_URL);
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}
