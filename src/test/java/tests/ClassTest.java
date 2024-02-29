package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import pages.*;
import webhooks.WebHook;

public class ClassTest extends WebHook {

    @Test
    void openJiraIFAuthPage() {
        JiraAuthPage.doLogin();
        SystemDashboardPage.stepToProject();
        TasksPage.getAmountTasks();
        String linkToTask = CreatTaskPage.createBag();
        SystemDashboardPage.stepToProject();
        TasksPage.getAmountTask2();
        Selenide.open(linkToTask);
        TaskPage.changeStatusOfTask();
        Selenide.sleep(10000);
    }

    @Test
    void openSystemDashboardPage() {

    }
}
