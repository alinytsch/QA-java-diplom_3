package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By logo = By.className("AppHeader_header__logo__2D0X2");
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By activeTab = By.className("tab_tab_type_current__2BEPc");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие кнопки 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Переход в личный кабинет")
    public void clickPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Переход в конструктор через кнопку")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Переход в конструктор через логотип")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Клик на вкладку 'Булки'")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Клик на вкладку 'Соусы'")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Клик на вкладку 'Начинки'")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Получение активной вкладки")
    public String getActiveTabText() {
        WebElement active = driver.findElement(activeTab);
        return active.getText();
    }
}
