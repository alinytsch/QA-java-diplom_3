package tests.profile;

import helpers.Endpoints;
import helpers.UserApiHelper;
import helpers.UserData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import tests.BaseUITest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class ProfileNavigationTest extends BaseUITest {
    private final String browser;

    private String email;
    private String password;
    private String name;
    private String accessToken;

    public ProfileNavigationTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Object[][] getBrowsers() {
        return new Object[][]{
                {"chrome"},
                {"yandex"}
        };
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

        CourierModel courier = new CourierModel(email, password, name);
        Response response = UserApiHelper.register(courier);
        accessToken = response.jsonPath().getString("accessToken");
    }

    @After
    public void tearDownUser() {
        if (accessToken != null) {
            UserApiHelper.delete(accessToken);
        }
    }

    protected void loginThroughUI() {
        driver.get(Endpoints.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        loginPage.waitUntilRedirectToMain();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToProfileFromMainPage() {
        loginThroughUI();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        assertThat(driver.getCurrentUrl(), equalTo(Endpoints.PROFILE_URL));
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutFromProfile() {
        loginThroughUI();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoutButton();
        assertThat(driver.getCurrentUrl(), equalTo(Endpoints.LOGIN_URL));
    }

    @Test
    @DisplayName("Переход в конструктор через логотип")
    public void goToConstructorFromLogo() {
        loginThroughUI();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLogo();
        assertThat(driver.getCurrentUrl(), equalTo(Endpoints.BASE_URL));
    }

    @Test
    @DisplayName("Переход в конструктор через кнопку 'Конструктор'")
    public void goToConstructorFromButton() {
        loginThroughUI();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickConstructorButton();
        assertThat(driver.getCurrentUrl(), equalTo(Endpoints.BASE_URL));
    }
}
