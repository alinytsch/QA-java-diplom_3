package tests.auth;

import helpers.UserData;
import io.qameta.allure.junit4.DisplayName;
import models.Browsers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import tests.BaseUITest;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class RegistrationTest extends BaseUITest {

    private static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    private static final String ERROR_SHORT_PASSWORD = "Некорректный пароль";

    private final String browser;

    public RegistrationTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Object[][] getBrowsers() {
        return Browsers.getBrowserData().toArray(new Object[0][]);
    }

    @Override
    protected String getBrowser() {
        return browser;
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);

        String name = UserData.randomName();
        String email = UserData.randomEmail();
        String password = "123456"; // валидный пароль

        registerPage.waitForPageLoad()
                .enterName(name)
                .enterEmail(email)
                .enterPassword(password)
                .clickRegisterButton();

        registerPage.waitUntilUrlIs(LOGIN_PAGE_URL);

        assertThat("Проверяем URL после успешной регистрации",
                driver.getCurrentUrl(), equalTo(LOGIN_PAGE_URL));
    }

    @Test
    @DisplayName("Ошибка при коротком пароле")
    public void testRegistrationWithShortPassword() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);

        String name = UserData.randomName();
        String email = UserData.randomEmail();
        String shortPassword = "123"; // короткий пароль

        registerPage.waitForPageLoad()
                .enterName(name)
                .enterEmail(email)
                .enterPassword(shortPassword)
                .clickRegisterButton();

        registerPage.waitForErrorVisible();

        assertThat("Проверяем текст ошибки при коротком пароле",
                registerPage.getErrorText(), equalTo(ERROR_SHORT_PASSWORD));
    }
}
