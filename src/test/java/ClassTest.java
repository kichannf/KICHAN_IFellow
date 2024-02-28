import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import pages.JiraAuthPage;

public class ClassTest extends WebHook {
    @Test
    void openJiraIFAuthPage() {

        Selenide.open("https://edujira.ifellow.ru");
        // Selenide.sleep(6000);
        JiraAuthPage.clickButton();
        Selenide.sleep(6000);
    }
}
