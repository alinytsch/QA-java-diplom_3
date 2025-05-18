package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import models.WebDriverCreator;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BaseUITest {
    protected WebDriver driver;

    protected abstract String getBrowser();

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = WebDriverCreator.createWebDriver(getBrowser());
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
