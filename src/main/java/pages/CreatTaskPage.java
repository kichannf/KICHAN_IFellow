package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Создание новой задачи/бага
 */
public class CreatTaskPage {

    private static final SelenideElement createTaskButton = $x("//a[text()='Создать']");
    private static final SelenideElement addSummuryInput = $x("//input[@id='summary']");
    private static final SelenideElement addDescriptionTextarea = $x("//textarea[@id='description']");
    private static final SelenideElement confirmCreateTaskButton = $x("//input[@value='Создать']");
    private static final SelenideElement assignTaskYourself = $x("//button[text()='Назначить меня']");
    private static final SelenideElement tryPullTaskNumber = $x("//a[@class='issue-created-key issue-link']");
    private static final String TEXT_SUMMARY = "Ошибка формы авторизации";
    private static final String TEXT_DESCRIPTION = "Авторизация с корректным логином вызывает ошибку входа";

    /** Создает баг и возравращает ссылку на него */
    public static String createBag() {
        createTaskButton.click();
        addSummuryInput.setValue(TEXT_SUMMARY);
        addDescriptionTextarea.setValue(TEXT_DESCRIPTION);
        assignTaskYourself.click();
        confirmCreateTaskButton.click();
        return tryPullTaskNumber.getAttribute("href");
    }
}
