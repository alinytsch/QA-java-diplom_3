package tests.constructor;

import io.qameta.allure.junit4.DisplayName;
import models.Browsers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.ConstructorPage;
import pages.MainPage;
import tests.BaseUITest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class ConstructorSectionTest extends BaseUITest {

    private final String browser;

    public ConstructorSectionTest(String browser) {
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
    @DisplayName("Переход на вкладку 'Булки'")
    public void openBunsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab(); // сбросить фокус
        mainPage.clickBunsTab();

        ConstructorPage page = new ConstructorPage(driver);
        assertThat(page.getActiveTabText(), equalTo("Булки"));
    }

    @Test
    @DisplayName("Переход на вкладку 'Соусы'")
    public void openSaucesTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();

        ConstructorPage page = new ConstructorPage(driver);
        assertThat(page.getActiveTabText(), equalTo("Соусы"));
    }

    @Test
    @DisplayName("Переход на вкладку 'Начинки'")
    public void openFillingsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();

        ConstructorPage page = new ConstructorPage(driver);
        assertThat(page.getActiveTabText(), equalTo("Начинки"));
    }
}