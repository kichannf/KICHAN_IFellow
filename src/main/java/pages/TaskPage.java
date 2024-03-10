package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

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


    public String checkStatus() {
        return checkStatusTask.getText();
    }

    public String checkVersionDepend() {
        return checkVersionDependTask.getText();
    }

    public String getAmountTasks() {
        return amountTasksPath.getText().split(" ")[2];
    }

    public void taskToProgress() {
        toInProgressStatusButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    public void taskToPComplete() {
        businessProcessButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        toCompleteStatusButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }
}
