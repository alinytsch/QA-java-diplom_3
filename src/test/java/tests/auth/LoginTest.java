package tests.auth;

import helpers.UserApiHelper;
import helpers.UserData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.Browsers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.*;
import tests.BaseUITest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@RunWith(Parameterized.class)
public class LoginTest extends BaseUITest {
    private final String browser;
    private String email;
    private String password;
    private String name;
    private String accessToken;

    public LoginTest(String browser) {
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

    @Before
    public void setUpUser() {
        email = UserData.randomEmail();
        password = UserData.randomPassword();
        name = UserData.randomName();

        Response response = UserApiHelper.register(email, password, name);
        accessToken = response.jsonPath().getString("accessToken");
    }

    @After
    public void tearDownUser() {
        if (accessToken != null) {
            UserApiHelper.delete(accessToken);
        }
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    public void loginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertThat(driver.getCurrentUrl(), containsString("/"));
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void loginFromAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertThat(driver.getCurrentUrl(), containsString("/"));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegisterForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertThat(driver.getCurrentUrl(), containsString("/"));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromForgotPasswordForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.clickLoginLink();

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertThat(driver.getCurrentUrl(), containsString("/"));
    }
}