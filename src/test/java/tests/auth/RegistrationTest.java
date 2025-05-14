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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class RegistrationTest extends BaseUITest {

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
        String password = "123456";

        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();

        assertThat(driver.getCurrentUrl(), containsString("/login"));
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
        String password = "123"; // короткий пароль

        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();

        assertThat(registerPage.getErrorText(), equalTo("Некорректный пароль"));
    }
}