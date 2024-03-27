package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

/** Класс для работы с задачами. */
public class TaskPage {
    private static final SelenideElement businessProcessButton = $x(
            "//span[text()='Бизнес-процесс']").as("Перейти к выбору статуса задачи");
    private static final SelenideElement toInProgressStatusButton = $x(
            "//span[text()='В работе']").as("Выбрать статус \"В работе\"");
    private static final SelenideElement toCompleteStatusButton = $x(
            "//span[text()='Выполнено']").as("Выбрать статус \"Выполнено\"");
    private static final SelenideElement checkStatusTask =
            $x("//strong[text()='Статус:']/following-sibling::span").as("Поле статуса задачи");
    private static final SelenideElement checkVersionDependTask =
            $x("//strong[@title='Исправить в версиях']/following-sibling::span").as("Поле привязка к версии задачи");
    private static final SelenideElement amountTasksPath = $x(
            "//div[@class='showing']/span").as("Общее число задач");;

    @Step("Вернуть статус задачи")
    public static String checkStatus() {
        refresh();
        return checkStatusTask.shouldBe(Condition.visible).getText();
    }

    @Step("Вернуть привязку к версии")
    public static String checkVersionDepend() {
        refresh();
        return checkVersionDependTask.shouldBe(Condition.visible).getText();
    }

    @Step("Вернуть количество созданных задач в проекте")
    public static int getAmountTasks() {
        refresh();
        return Integer.parseInt(amountTasksPath.getText().split(" ")[2]);
    }

    @Step("Выставить задаче {taskLink} статус в \"В РАБОТЕ\"")
    public static void taskToProgress(String taskLink) {
        open(taskLink);
        toInProgressStatusButton.click();
        checkStatusTask.shouldHave(Condition.exactText("В РАБОТЕ"));
    }

    @Step("Выставить задаче {taskLink} статус в \"ГОТОВО\"")
    public static void taskToPComplete(String taskLink) {
        open(taskLink);
        businessProcessButton.click();
        toCompleteStatusButton.click();
        checkStatusTask.shouldHave(Condition.exactText("ГОТОВО"));;
    }
}
