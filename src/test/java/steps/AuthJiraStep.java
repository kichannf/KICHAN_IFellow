package steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.JiraAuthPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class AuthJiraStep {

    private static final String TITLE_LOGIN = "Добро пожаловать в Jira";
    private final JiraAuthPage pageAuth = new JiraAuthPage();
    private static final String START_PAGE_URL = "https://edujira.ifellow.ru";

    @Если("Ввести валидный логин и пароль")
    public void openAuthPageTest(){
        if (pageAuth.checkAuth()) {
            Selenide.open(START_PAGE_URL);
            getWebDriver().manage().window().maximize();
            pageAuth.doLogin();
        } else Selenide.open(START_PAGE_URL);
    }

    @Тогда("Успешная авторизация")
    public void authJiraTest(){Assertions.assertEquals(TITLE_LOGIN, pageAuth.checkLoginSuccess());
    }
}
