package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;
    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//input[@name='Пароль']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[contains(@class, 'Auth_link') and @href='/login']");
    private final By errorMessage = By.className("input__error");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод имени")
    public void enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("Ввод email")
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажатие кнопки регистрации")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Переход на форму логина")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Получение текста ошибки")
    public String getErrorText() {
        return driver.findElement(errorMessage).getText();
    }

}