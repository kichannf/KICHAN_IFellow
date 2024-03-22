package tests;

import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.JiraAuthPage;
import webhooks.WebHook;

/** Тест успеха авторизации*/
public class AuthJiraTest extends WebHook {
    @Epic("Авторизация")
    @DisplayName("Login")
    @Test
    void authJiraTest(){
        Assertions.assertTrue(JiraAuthPage.checkAuth());
    }
}
