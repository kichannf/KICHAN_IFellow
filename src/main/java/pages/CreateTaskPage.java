package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;


/** Создание новой задачи. */
public class CreateTaskPage {

    private static final SelenideElement createTaskButton = $x("//a[text()='Создать']");
    private static final SelenideElement addSummaryInput = $x("//input[@id='summary']");
    private static final SelenideElement addDescriptionTextarea = $x("//textarea[@id='description']");
    private static final SelenideElement confirmCreateTaskButton = $x("//input[@value='Создать']");
    private static final SelenideElement assignTaskYourself = $x("//button[text()='Назначить меня']");
    private static final SelenideElement saveIdNewTask = $x("//a[@class='issue-created-key issue-link']");
    private static final String TEXT_SUMMARY = "Ошибка формы авторизации";
    private static final String TEXT_DESCRIPTION = "Авторизация с корректным логином вызывает ошибку входа";

    /** Создает, заполняет и назначает на авторизованного юзера баг
     *  возравращает ссылку на задачу*/
    public String createBag() {
        createTaskButton.click();
        addSummaryInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(TEXT_SUMMARY);
        addDescriptionTextarea.setValue(TEXT_DESCRIPTION);
        assignTaskYourself.click();
        confirmCreateTaskButton.click();
        return saveIdNewTask.shouldBe(Condition.visible, Duration.ofSeconds(10)).getAttribute("href");
    }
}
