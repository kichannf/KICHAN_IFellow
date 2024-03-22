package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

/** Создание новой задачи. */
public class CreateTaskPage {

    private static final SelenideElement createTaskButton = $x("//a[text()='Создать']").as("Открыть форму \"Создать задачу\"");
    private static final SelenideElement addSummaryInput = $x("//input[@id='summary']").as("Поле \"Тема задачи\"");
    private static final SelenideElement addDescriptionTextarea = $x("//textarea[@id='description']").as("Поле \"Описание задачи\"");
    private static final SelenideElement confirmCreateTaskButton = $x("//input[@value='Создать']").as("Подтвердить создание задачи");
    private static final SelenideElement assignTaskYourself = $x("//button[text()='Назначить меня']").as("Назначить задачу на текущего пользователя");
    private static final SelenideElement saveIdNewTask = $x("//a[@class='issue-created-key issue-link']").as("Сохранить ссылку на созданную задачу");
    private static final String TEXT_SUMMARY = "Ошибка формы авторизации";
    private static final String TEXT_DESCRIPTION = "Авторизация с корректным логином вызывает ошибку входа";

    /** Создает, заполняет и назначает на авторизованного юзера баг
     *  возравращает ссылку на задачу*/
    public static String createBag() {
        createTaskButton.click();
        addSummaryInput.setValue(TEXT_SUMMARY);
        addDescriptionTextarea.setValue(TEXT_DESCRIPTION);
        assignTaskYourself.click();
        confirmCreateTaskButton.click();
        return saveIdNewTask.getAttribute("href");
    }
}
