package tests;

import helpers.Endpoints;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pages.MainPage;

import java.time.Duration;

public abstract class BaseUITest {
    protected WebDriver driver;

    protected abstract String getBrowser();

    @Before
    public void setUp() {
        String browser = getBrowser();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");

        if (browser.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", Endpoints.YANDEX_DRIVER_PATH);
            options.setBinary(Endpoints.YANDEX_BINARY_PATH);
        } else {
            WebDriverManager.chromedriver().driverVersion("136.0.7103.114").setup();
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(Endpoints.BASE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void loginThroughUI(String email, String password) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
    }
}
