package pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class TasksPage {

    private static final SelenideElement amount_tasks_path = $x("//div[@class='showing']/span");
    private static final SelenideElement search_field = $x("//input[@id='quickSearchInput']");
    public static final SelenideElement select_project = $x("//ol[@class='issue-list']/child::li/child::a");
    public static final SelenideElement check_status_test_selenium = $x("//strong[text()='Статус:']/following-sibling::span");
    public static final SelenideElement check_version_depend = $x("//strong[@title='Исправить в версиях']/following-sibling::span");
    private static final String name_of_task = "TestSelenium";

    public static void getAmountTasks() {
        String[] amount_tasks = amount_tasks_path.getText().split(" ");
        search_field.setValue(name_of_task).pressEnter();
        select_project.click();
        String status_of_task = check_status_test_selenium.getText();
        System.out.println(status_of_task);
        String version_depend = check_version_depend.getText();
        System.out.println(version_depend);


    }
}
