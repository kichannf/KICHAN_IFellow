package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/** Страница авторизации и Login */
public class JiraAuthPage {

    private static final SelenideElement inputLoginButton = $x("//input[@id='login-form-username']").as("Поле логина");
    private static final SelenideElement inputPasswordButton = $x("//input[@id='login-form-password']").as("Поле пароля");
    private static final SelenideElement inputLogin = $x("//input[@id='login' and @type='submit']").as("Кнопка входа");
    private static final SelenideElement titleLogin = $x("//h3[text()='Добро пожаловать в Jira']").as("Маркер успешной авторизации");
    private static final SelenideElement toStartPage = $x("//a[@aria-label='Перейти на главную страницу']").as("Переход на главную страницу");

    @Step("Войти в систему с {login} и {password}")
    public static void doLogin(String login, String password){
        inputLoginButton.setValue(login);
        inputPasswordButton.setValue(password);
        inputLogin.click();
        titleLogin.shouldBe(Condition.visible);
    }

    @Step("Выполнена ли авторизация в системе")
    public static Boolean checkAuth(){
        toStartPage.click();
        return titleLogin.exists();
    }
}
