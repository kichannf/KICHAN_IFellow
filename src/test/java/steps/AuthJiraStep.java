package steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.JiraAuthPage;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class AuthJiraStep {

    private static final String TITLE_LOGIN = "Добро пожаловать в Jira";
    private final JiraAuthPage pageAuth = new JiraAuthPage();


    @Если("Ввести валидный логин и пароль")
    public void openAuthPageTest(){
        if (pageAuth.checkAuth()) {
            Selenide.open("https://edujira.ifellow.ru");
            getWebDriver().manage().window().maximize();
            getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            pageAuth.doLogin();
        } else Selenide.open("https://edujira.ifellow.ru");
    }

    @Тогда("Успешная авторизация")
    public void authJiraTest(){
        Assertions.assertEquals(TITLE_LOGIN, pageAuth.checkLoginSuccess());
    }
}
