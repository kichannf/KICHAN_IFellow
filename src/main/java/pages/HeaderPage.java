package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;


/** Навигация через верхнюю панель сайта. */
public class HeaderPage {

    private final SelenideElement projectButton = $x("//a[text()='Проекты']");
    private final SelenideElement stepToProjectButton = $x("//a[text()='Test (TEST)']");
    private final SelenideElement nameProjectTitle =
            $x("//div[@class='aui-item project-title']/child::a");
    private final SelenideElement searchField = $x("//input[@id='quickSearchInput']");
    private final SelenideElement filterTasks = $x("//div[@data-id='issuetype']");
    private final SelenideElement clickFilterTasks = $x("//label[@title='Задача']");
    private static final String nameOfTask = "TestSelenium";


    public void stepToProject() {
        projectButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        stepToProjectButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    public String checkProjectOpen() {
        return nameProjectTitle.shouldBe(Condition.visible, Duration.ofSeconds(10)).getText();
    }

    public void searchTask() {
        searchField.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(nameOfTask).pressEnter();
        filterTasks.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        clickFilterTasks.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        clickFilterTasks.pressEnter();
    }
}
