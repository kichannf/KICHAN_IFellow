package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CreateTaskPage;
import pages.HeaderPage;
import pages.TaskPage;
import webhooks.WebHook;

/** Тест кейса HW_6*/
public class ActionsTest extends WebHook {

    private static final String NAME_PROJECT = "Test";
    private static final String STATUS_SELENIUM_TEST = "СДЕЛАТЬ";
    private static final String VERSION_DEPEND_SELENIUM_TEST = "Version 2.0";
    private static final String TASK_NAME = "TestSelenium";

    @Epic(value = "Действия")
    @Feature(value = "Работа с проектом")
    @DisplayName("Проверка открытия проекта")
    @Test
    void openProject() {
        HeaderPage.stepToProject();
        Assertions.assertEquals(NAME_PROJECT, HeaderPage.checkProjectOpen());
    }

    @Epic(value = "Действия")
    @Feature(value = "Работа с задачей")
    @DisplayName("Проверка статуса задачи")
    @Test
    void checkTaskStatus() {
        HeaderPage.searchTask(TASK_NAME);
        Assertions.assertEquals(STATUS_SELENIUM_TEST, TaskPage.checkStatus());
    }

    @Epic(value = "Действия")
    @Feature(value = "Работа с задачей")
    @DisplayName("Проверка привязки задачи к версии")
    @Test
    void checkTaskVersionDepend() {
        HeaderPage.searchTask(TASK_NAME);
        Assertions.assertEquals(VERSION_DEPEND_SELENIUM_TEST, TaskPage.checkVersionDepend());
    }

    @Epic(value = "Действия")
    @Feature(value = "Работа с багом")
    @DisplayName("Проверка создания бага")
    @Test
    void checkCreateBug() {
        HeaderPage.stepToProject();
        int amount_tasks_before = TaskPage.getAmountTasks();
        CreateTaskPage.createBag();
        int amount_tasks_after = TaskPage.getAmountTasks();
        Assertions.assertTrue(amount_tasks_after > amount_tasks_before);
    }

    @Epic(value = "Действия")
    @Feature(value = "Работа с багом")
    @DisplayName("Смена статусов бага")
    @Test
    void checkStatusBag() {
        String link = CreateTaskPage.createBag();
        TaskPage.taskToProgress(link);
        Assertions.assertEquals("В РАБОТЕ", TaskPage.checkStatus());
        TaskPage.taskToPComplete(link);
        Assertions.assertEquals("ГОТОВО", TaskPage.checkStatus());
    }
}
