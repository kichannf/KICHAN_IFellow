package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TasksPage {

    private static final SelenideElement amountTasksPath = $x("//div[@class='showing']/span");
    private static final SelenideElement searchField = $x("//input[@id='quickSearchInput']");
    public static final SelenideElement selectProject = $x("//ol[@class='issue-list']/child::li/child::a");
    public static final SelenideElement checkStatusTestSelenium = $x("//strong[text()='Статус:']/following-sibling::span");
    public static final SelenideElement checkVersionDepend = $x("//strong[@title='Исправить в версиях']/following-sibling::span");
    private static final String nameOfTask = "TestSelenium";

    public static void getAmountTask2() {
        String amount_tasks2 = amountTasksPath.getText().split(" ")[2];
        System.out.println(amount_tasks2);
    }

    public static void getAmountTasks() {
        String amount_tasks = amountTasksPath.getText().split(" ")[2];
        System.out.println(amount_tasks);
        searchField.setValue(nameOfTask).pressEnter();
        selectProject.click();
        String status_of_task = checkStatusTestSelenium.getText();
        System.out.println(status_of_task);
        String version_depend = checkVersionDepend.getText();
        System.out.println(version_depend);


    }
}
