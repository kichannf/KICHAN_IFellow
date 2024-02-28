import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import pages.JiraAuthPage;
import pages.SystemDashboardPage;

public class ClassTest extends WebHook {
    @Test
    void openJiraIFAuthPage() {

        Selenide.open("https://edujira.ifellow.ru");
        // Selenide.sleep(6000);
        JiraAuthPage.clickButton();
        SystemDashboardPage.stepToProject();

        Selenide.sleep(6000);
    }

    @Test
    void openSystemDashboardPage() {

    }
}
