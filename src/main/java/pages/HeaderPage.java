package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


/** Навигация через верхнюю панель сайта. */
public class HeaderPage {

    private static final SelenideElement projectButton = $x("//a[text()='Проекты']");
    private static final SelenideElement stepToProjectButton = $x("//a[text()='Test (TEST)']");
    private static final SelenideElement nameProjectTitle =
            $x("//div[@class='aui-item project-title']/child::a");
    private static final SelenideElement searchField = $x("//input[@id='quickSearchInput']");
    public static final SelenideElement selectProjectFromSearch =
            $x("//ol[@class='issue-list']/child::li/child::a");
    private static final String nameOfTask = "TestSelenium";


    public static void stepToProject() {
        projectButton.click();
        stepToProjectButton.click();
    }

    public static String checkProjectOpen() {
        stepToProject();
        return nameProjectTitle.shouldBe(Condition.visible).getText();
    }

    public static void searchTask() {
        searchField.setValue(nameOfTask).pressEnter();
        selectProjectFromSearch.click();
    }
}
