package tests.constructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.ConstructorPage;
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
        return new Object[][] {
                {"chrome"},
                {"yandex"}
        };
    }

    @Override
    protected String getBrowser() {
        return browser;
    }

    @Test
    public void switchingToFillingsTabWorks() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openFillingsTab();
        String activeTab = constructorPage.getActiveTabText();
        assertThat(activeTab, equalTo("Начинки"));
    }

    @Test
    public void switchingToSaucesTabWorks() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openSaucesTab();
        String activeTab = constructorPage.getActiveTabText();
        assertThat(activeTab, equalTo("Соусы"));
    }

    @Test
    public void switchingToBunsTabWorks() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openBunsTab();
        String activeTab = constructorPage.getActiveTabText();
        assertThat(activeTab, equalTo("Булки"));
    }
}
