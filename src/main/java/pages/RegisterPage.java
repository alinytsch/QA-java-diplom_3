package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//input[@type='password']"); // поправил локатор
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[contains(@class, 'Auth_link') and @href='/login']");
    private final By errorMessage = By.className("input__error");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ожидание загрузки страницы регистрации")
    public RegisterPage waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton));
        return this;
    }

    @Step("Ввод имени: {name}")
    public RegisterPage enterName(String name) {
        var element = driver.findElement(nameInput);
        element.clear();
        element.sendKeys(name);
        return this;
    }

    @Step("Ввод email: {email}")
    public RegisterPage enterEmail(String email) {
        var element = driver.findElement(emailInput);
        element.clear();
        element.sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegisterPage enterPassword(String password) {
        var element = driver.findElement(passwordInput);
        element.clear();
        element.sendKeys(password);
        return this;
    }

    @Step("Нажатие кнопки регистрации")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Нажатие ссылки перехода на форму логина")
    public RegisterPage clickLoginLink() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginLink))
                .click();
        return this;
    }

    @Step("Ожидание перехода на URL: {url}")
    public RegisterPage waitUntilUrlIs(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
        return this;
    }

    @Step("Ожидание отображения ошибки")
    public RegisterPage waitForErrorVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return this;
    }

    @Step("Получение текста ошибки")
    public String getErrorText() {
        return driver.findElement(errorMessage).getText();
    }
}
