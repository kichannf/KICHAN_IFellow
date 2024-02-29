package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

/** Страница созданой задачи. */
public class CreatedTaskPage {

    private static final SelenideElement businessProcessButton = $x("//span[text()='Бизнес-процесс']");
    private static final SelenideElement toInProgressStatusButton = $x("//span[text()='В работе']");
    private static final SelenideElement toCompleteStatusButton = $x("//span[text()='Выполнено']");

    public static void changeStatusOfTask() {
        toInProgressStatusButton.click();
        businessProcessButton.click();
        toCompleteStatusButton.click();
    }
}
