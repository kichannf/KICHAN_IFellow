import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


abstract public class WebHook {

    @BeforeAll
    public static void setup(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }
    @Test
    void openJiraIFAuthPage() {
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();

        Selenide.open("https://edujira.ifellow.ru");
        Selenide.sleep(10000);
    }
}
