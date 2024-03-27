package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CreateTaskPage;
import pages.HeaderPage;
import pages.JiraAuthPage;
import pages.TaskPage;
import webhooks.WebHook;

public class ActionsTest extends WebHook {
    @Epic("Авторизация")
    @DisplayName("Login")
    @Test
    void authJiraTest() {Assertions.assertTrue(JiraAuthPage.checkAuth());}

    @Epic(value = "Действия")
    @Feature(value = "Работа с проектом")
    @DisplayName("Проверка открытия проекта")
    @Test
    void openProject() {
        HeaderPage.stepToProject();
        Assertions.assertEquals("Test", HeaderPage.checkProjectOpen());
    }

    @Epic(value = "Действия")
    @Feature(value = "Работа с задачей")
    @DisplayName("Проверка статуса задачи")
    @Test
    void checkTaskStatus() {
        HeaderPage.searchTask(System.getProperty("task.name"));
        Assertions.assertEquals("СДЕЛАТЬ", TaskPage.checkStatus());
    }

    @Epic(value = "Действия")
    @Feature(value = "Работа с задачей")
    @DisplayName("Проверка привязки задачи к версии")
    @Test
    void checkTaskVersionDepend() {
        HeaderPage.searchTask(System.getProperty("task.name"));
        Assertions.assertEquals("Version 2.0", TaskPage.checkVersionDepend());
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
