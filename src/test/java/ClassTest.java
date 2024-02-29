import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import pages.*;

public class ClassTest extends WebHook {



    @Test
    void openJiraIFAuthPage() {
        JiraAuthPage.clickButton();
        SystemDashboardPage.stepToProject();
        TasksPage.getAmountTasks();
        String linkToTask = CreatTaskPage.createBag();
        SystemDashboardPage.stepToProject();
        TasksPage.getAmountTask2();
        Selenide.open(linkToTask);
        CreatedTaskPage.changeStatusOfTask();
        Selenide.sleep(10000);
    }

    @Test
    void openSystemDashboardPage() {

    }
}
