package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


/** Страница авторизации edujira.ifellow.ru и Login */
public class JiraAuthPage {

    private static final SelenideElement inputLoginButton = $x("//input[@id='login-form-username']");
    private static final SelenideElement inputPasswordButton = $x("//input[@id='login-form-password']");
    private static final SelenideElement inputLogin = $x("//input[@id='login' and @type='submit']");
    private static final SelenideElement titleAuth = $x("//h3[text()='Вход в систему']");
    private static final SelenideElement titleLogin = $x("//h3[text()='Добро пожаловать в Jira']");
    private static final String login = "AT6";
    private static final String password = "Qwerty123";

    public static void doLogin(){

        inputLoginButton.setValue(login);
        inputPasswordButton.setValue(password);
        inputLogin.click();
    }

    public static String checkPageOpen(){
        return titleAuth.getText();
    }

    public static String checkLoginSuccess(){
        doLogin();
        return titleLogin.getText();
    }

}
