package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {
    private final WebDriver driver;
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By activeTab = By.xpath("//div[contains(@class,'tab_tab_type_current')]/span");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть вкладку 'Булки'")
    public void openBunsTab() {
        WebElement tab = driver.findElement(bunsTab);
        new Actions(driver).moveToElement(tab).click().perform();
    }

    @Step("Открыть вкладку 'Соусы'")
    public void openSaucesTab() {
        WebElement tab = driver.findElement(saucesTab);
        new Actions(driver).moveToElement(tab).click().perform();
    }

    @Step("Открыть вкладку 'Начинки'")
    public void openFillingsTab() {
        WebElement tab = driver.findElement(fillingsTab);
        new Actions(driver).moveToElement(tab).click().perform();
    }

    @Step("Получение текста активной вкладки")
    public String getActiveTabText() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(activeTab));
        return driver.findElement(activeTab).getText();
    }
}
