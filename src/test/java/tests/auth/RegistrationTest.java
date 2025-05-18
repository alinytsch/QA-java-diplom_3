package tests.auth;

import helpers.UserData;
import io.qameta.allure.junit4.DisplayName;
import models.Browsers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import tests.BaseUITest;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
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
        String password = "123456"; // валидный пароль

        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();

        // Ждем редирект на страницу логина
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));

        assertThat(driver.getCurrentUrl(), equalTo("https://stellarburgers.nomoreparties.site/login"));
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
