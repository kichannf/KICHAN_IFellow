package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/** Класс для работы с задачами. */
public class TaskPage {

    private static final SelenideElement businessProcessButton = $x("//span[text()='Бизнес-процесс']");
    private static final SelenideElement toInProgressStatusButton = $x("//span[text()='В работе']");
    private static final SelenideElement toCompleteStatusButton = $x("//span[text()='Выполнено']");
    private static final SelenideElement checkStatusTask =
            $x("//strong[text()='Статус:']/following-sibling::span");
    private static final SelenideElement checkVersionDependTask =
            $x("//strong[@title='Исправить в версиях']/following-sibling::span");
    private static final SelenideElement amountTasksPath = $x("//div[@class='showing']/span");


    public static String checkStatus() {
        return checkStatusTask.shouldBe(Condition.visible).getText();
    }

    public static String checkVersionDepend() {
        return checkVersionDependTask.getText();
    }

    public static String getAmountTasks() {
        return amountTasksPath.getText().split(" ")[2];
    }

    public static void taskToProgress() {
        toInProgressStatusButton.click();
    }

    public static void taskToPComplete() {
        businessProcessButton.click();
        toCompleteStatusButton.click();
    }
}
