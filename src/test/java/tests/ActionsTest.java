package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CreateTaskPage;
import pages.HeaderPage;
import pages.JiraAuthPage;
import pages.TaskPage;
import webhooks.WebHook;

import static com.codeborne.selenide.Selenide.*;


/** Тест кейса HW_3*/
public class ActionsTest extends WebHook {

    private static final String NAME_PROJECT = "Test";
    private static final String STATUS_SELENIUM_TEST = "СДЕЛАТЬ";
    private static final String VERSION_DEPEND_SELENIUM_TEST = "Version 2.0";
    private static final SelenideElement titleLogin = $x("//h3[text()='Добро пожаловать в Jira']");
    private static final int timeForLoad = 4000;

    @BeforeAll
    public static void login() {
        JiraAuthPage.doLogin();
        titleLogin.shouldBe(Condition.visible);
    }

    @DisplayName("Открытие проекта")
    @Test
    void openProject() {
        Assertions.assertEquals(NAME_PROJECT, HeaderPage.checkProjectOpen());
    }

    @DisplayName("Проверка статуса задачи")
    @Test
    void checkTaskStatus() {
        HeaderPage.searchTask();
        Selenide.sleep(timeForLoad);
        Assertions.assertEquals(STATUS_SELENIUM_TEST, TaskPage.checkStatus());
    }

    @DisplayName("Проверка привязки задачи к версии")
    @Test
    void checkTaskVersionDepend() {
        HeaderPage.searchTask();
        Assertions.assertEquals(VERSION_DEPEND_SELENIUM_TEST, TaskPage.checkVersionDepend());
    }

    @DisplayName("Проверка создания бага")
    @Test
    void checkCreateBug() {
        HeaderPage.stepToProject();
        int amount_tasks_before = Integer.parseInt(TaskPage.getAmountTasks());
        CreateTaskPage.createBag();
        Selenide.refresh();
        int amount_tasks_after = Integer.parseInt(TaskPage.getAmountTasks());
        Assertions.assertTrue(amount_tasks_after > amount_tasks_before);
    }

    @DisplayName("Смена статусов бага")
    @Test
    void checkStatusBag() {
        String link = CreateTaskPage.createBag();
        Selenide.open(link);
        TaskPage.taskToProgress();
        Selenide.sleep(timeForLoad);
        Assertions.assertEquals("В РАБОТЕ", TaskPage.checkStatus());
        TaskPage.taskToPComplete();
        Selenide.sleep(timeForLoad);
        Selenide.refresh();
        Assertions.assertEquals("ГОТОВО", TaskPage.checkStatus());
    }
}
