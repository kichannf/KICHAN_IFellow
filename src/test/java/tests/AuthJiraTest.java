package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.JiraAuthPage;
import webhooks.WebHook;


/** Тест открытия страницы Jira и авторизации*/
public class AuthJiraTest extends WebHook {

    private static final String TITLE_FORM_AUTH = "Вход в систему";
    private static final String TITLE_LOGIN = "Добро пожаловать в Jira";

    @DisplayName("Открытие страницы авторизации")
    @Test
    void openAuthPageTest(){
        Assertions.assertEquals(TITLE_FORM_AUTH, JiraAuthPage.checkPageOpen());
    }

    @DisplayName("Login")
    @Test
    void authJiraTest(){
        Assertions.assertEquals(TITLE_LOGIN, JiraAuthPage.checkLoginSuccess());
    }

}
