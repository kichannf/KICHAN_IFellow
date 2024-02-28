/**
 * Страница авторизации JiraIFellow
 */
package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class JiraAuthPage {

    private static final SelenideElement inputLoginButton = $x("//input[@id='login-form-username']");
    private static final SelenideElement inputPasswordButton = $x("//input[@id='login-form-password']");
    private static final SelenideElement inputLogin = $x("//input[@id='login' and @type='submit']");
    private static final String login = "AT6";
    private static final String password = "Qwerty123";

    public static void clickButton() {
        inputLoginButton.setValue(login);
        inputPasswordButton.setValue(password);
        inputLogin.click();
    }

}
