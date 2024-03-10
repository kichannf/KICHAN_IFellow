package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import org.junit.After;
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
public class ActionsStep {

    private static final String NAME_PROJECT = "Test";
    private static final String STATUS_SELENIUM_TEST = "СДЕЛАТЬ";
    private static final String VERSION_DEPEND_SELENIUM_TEST = "Version 2.0";
//    private static final SelenideElement titleLogin = $x("//h3[text()='Добро пожаловать в Jira']");
    private static final int timeForLoad = 4000;
    int amount_tasks_before;
    int amount_tasks_after;
    String link_bug;
    private final JiraAuthPage pageAuth = new JiraAuthPage();
    private final HeaderPage pageHeader = new HeaderPage();
    private final TaskPage pageTask = new TaskPage();
    private final CreateTaskPage pageCreateTask = new CreateTaskPage();
    private static boolean beforeExecuted = false;


//    @Before(value = "~@auth")
//    public void auth(){
//        Selenide.open("https://edujira.ifellow.ru");
//        pageAuth.doLogin();
//    }
//    @Дано("Авторизация")
//    public void beforeScenario() {
//        if (pageAuth.checkAuth()) {
//            System.out.println("Before");
//            Selenide.open("https://edujira.ifellow.ru");
//            pageAuth.doLogin();
//        }
//    }


    @Если("В поисковой строке искать и зайти в проект Test")
    public void openProject() {
        pageHeader.stepToProject();
    }

    @Тогда("Откроется проект Test")
    public void checkOpenProject() {
        Assertions.assertEquals(NAME_PROJECT, pageHeader.checkProjectOpen());
    }


    @Если("Найти и зайти в задачу TestSelenium")
    public void openTestSeleniumTask() {
        pageHeader.searchTask();
        Selenide.sleep(3000);
    }

    @Тогда("статус задачи Сделано")
    public void checkTaskStatus() {
        Selenide.sleep(3000);
        Assertions.assertEquals(STATUS_SELENIUM_TEST, pageTask.checkStatus());
    }

    @Тогда("задача привязана к версии Version 2.0")
    public void checkTaskVersionDepend() {
        Assertions.assertEquals(VERSION_DEPEND_SELENIUM_TEST, pageTask.checkVersionDepend());
    }

    @И("Создать баг и сохранить количество задач")
    public void createBug() {
        this.amount_tasks_before = Integer.parseInt(pageTask.getAmountTasks());
        pageCreateTask.createBag();
        Selenide.refresh();
        this.amount_tasks_after = Integer.parseInt(pageTask.getAmountTasks());
    }

    @Тогда("Баг создан")
    public void checkCreateBug() {
        Assertions.assertTrue(amount_tasks_after > amount_tasks_before);
    }

    @Если("Создать баг и сохранить на него ссылку")
    public void createBugSaveLink() {
        System.out.println("BUG");
        this.link_bug = pageCreateTask.createBag();
    }

    @И("Открыть созданный баг")
    public void openCreateBug() {
        Selenide.open(link_bug);
    }

    @И("Сменить статус на В РАБОТЕ")
    public void statusAtProgress() {
        pageTask.taskToProgress();
        Selenide.sleep(3000);
    }

    @Тогда("Статус бага В РАБОТЕ")
    public void checkStatusProgress() {
        Selenide.open(link_bug);
        Assertions.assertEquals("В РАБОТЕ", pageTask.checkStatus());
    }

    @И("Сменить статус на ГОТОВО")
    public void atStatusComplete() {
        pageTask.taskToPComplete();
        Selenide.sleep(3000);

    }

    @Тогда("Статус бага ГОТОВО")
    public void checkStatusComplete() {
        Selenide.open(link_bug);
        Assertions.assertEquals("ГОТОВО", pageTask.checkStatus());
    }
}
