package steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.CreateTaskPage;
import pages.HeaderPage;
import pages.TaskPage;


/** Тест кейса HW_4*/
public class ActionsStep {

    private static final String NAME_PROJECT = "Test";
    private static final String STATUS_SELENIUM_TEST_TO_DO = "СДЕЛАТЬ";
    private static final String STATUS_SELENIUM_TEST_IN_PROGRESS = "В РАБОТЕ";
    private static final String STATUS_SELENIUM_TEST_IN_COMPLETE = "ГОТОВО";
    private static final String VERSION_DEPEND_SELENIUM_TEST = "Version 2.0";
    private final int TIME_FOR_LOAD_PAGE = 4000;
    private final HeaderPage pageHeader = new HeaderPage();
    private final TaskPage pageTask = new TaskPage();
    private final CreateTaskPage pageCreateTask = new CreateTaskPage();
    int amount_tasks_before;
    int amount_tasks_after;
    String link_bug;


    @Если("В поисковой строке искать и зайти в проект Test")
    public void openProject() {pageHeader.stepToProject();}

    @Тогда("Откроется проект Test")
    public void checkOpenProject() {Assertions.assertEquals(NAME_PROJECT, pageHeader.checkProjectOpen());}

    @Если("Найти и зайти в задачу TestSelenium")
    public void openTestSeleniumTask() {
        pageHeader.searchTask();
        Selenide.sleep(TIME_FOR_LOAD_PAGE);
    }

    @Тогда("статус задачи Сделать")
    public void checkTaskStatus() {
        Selenide.sleep(TIME_FOR_LOAD_PAGE);
        Assertions.assertEquals(STATUS_SELENIUM_TEST_TO_DO, pageTask.checkStatus());
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
        this.link_bug = pageCreateTask.createBag();
    }

    @И("Открыть созданный баг")
    public void openCreateBug() {Selenide.open(link_bug);}

    @И("Сменить статус на В РАБОТЕ")
    public void statusAtProgress() {
        pageTask.taskToProgress();
        Selenide.sleep(TIME_FOR_LOAD_PAGE);
    }

    @Тогда("Статус бага В РАБОТЕ")
    public void checkStatusProgress() {
        Selenide.open(link_bug);
        Assertions.assertEquals(STATUS_SELENIUM_TEST_IN_PROGRESS, pageTask.checkStatus());
    }

    @И("Сменить статус на ГОТОВО")
    public void atStatusComplete() {
        pageTask.taskToPComplete();
        Selenide.sleep(TIME_FOR_LOAD_PAGE);

    }

    @Тогда("Статус бага ГОТОВО")
    public void checkStatusComplete() {
        Selenide.open(link_bug);
        Assertions.assertEquals(STATUS_SELENIUM_TEST_IN_COMPLETE, pageTask.checkStatus());
    }
}
