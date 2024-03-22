package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/** Навигация через верхнюю панель сайта. */
public class HeaderPage {

    private static final SelenideElement projectButton = $x("//a[text()='Проекты']").as("Выбор проектов");
    private static final SelenideElement stepToProjectButton = $x("//a[text()='Test (TEST)']").as("Выбрать проект Test");
    private static final SelenideElement nameProjectTitle =
            $x("//div[@class='aui-item project-title']/child::a").as("Загаловок открытого проекта");
    private static final SelenideElement searchField = $x("//input[@id='quickSearchInput']").as("Поле поиска");
    public static final SelenideElement selectProjectFromSearch =
            $x("//ol[@class='issue-list']/child::li/child::a").as("Взять первую задачу из поиска");
    private static final SelenideElement filterTasks = $x("//div[@data-id='issuetype']").as("Сортировка задач по типу");
    private static final SelenideElement clickFilterTasks = $x("//label[@title='Задача']").as("Показать только Epic-и");

    @Step("Открыть проект Test")
    public static void stepToProject() {
        projectButton.click();
        stepToProjectButton.click();
        nameProjectTitle.shouldBe(Condition.visible);
    }

    @Step("Вернуть название открытого проекта")
    public static String checkProjectOpen() {
        return nameProjectTitle.shouldBe(Condition.visible).getText();
    }

    @Step("Перейти в задачу {nameOfTask}")
    public static void searchTask(String nameOfTask) {
        searchField.setValue(nameOfTask).pressEnter();
        filterTasks.click();
        clickFilterTasks.click();
        selectProjectFromSearch.pressEnter();
    }
}
