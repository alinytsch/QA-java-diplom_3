package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By emailInput = By.xpath("//input[@name='name' or @name='email']");
    private final By passwordInput = By.xpath("//input[@name='Пароль' or @type='password']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(emailInput))
                .sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordInput))
                .sendKeys(password);
    }

    @Step("Нажатие кнопки 'Войти'")
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    @Step("Переход на форму регистрации")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/register"));
    }

    @Step("Переход на форму восстановления пароля")
    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/forgot-password"));
    }
}