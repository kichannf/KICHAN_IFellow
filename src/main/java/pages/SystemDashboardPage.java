/**
 * Главная страница. System Dashboard
 */
package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class SystemDashboardPage {

    private static final SelenideElement projectButton = $x("//a[text()='Проекты']");
    private static final SelenideElement stepToProjectButton = $x("//a[text()='Test (TEST)']");
    private static final SelenideElement nameProjectTitle =
            $x("//div[@class='aui-item project-title']/child::a");
    private static final SelenideElement searchField = $x("//input[@id='quickSearchInput']");
    private static final String nameOfTask = "TestSelenium";

    public static void stepToProject() {
        projectButton.click();
        stepToProjectButton.click();
    }

    public static String checkProjectOpen() {
        stepToProject();
        return nameProjectTitle.shouldBe(Condition.visible).getText();
    }
}
