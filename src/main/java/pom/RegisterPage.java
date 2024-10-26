package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameFieldOnRegisterPage = By.xpath(".//*[text()='Имя']/../*[@type='text']");
    private final By emailFieldOnRegisterPage = By.xpath(".//*[text()='Email']/../*[@type='text']");
    private final By passwordFieldOnRegisterPage = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");
    private final By registerButtonOnRegisterPage = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By incorrectPasswordMessage = By.xpath(".//p[@class='input__error text_type_main-default' and text()='Некорректный пароль']");
    private final By entryButtonOnRegisterPage = By.xpath(".//a[text()='Войти']");
    private final By registerPageHeader = By.xpath(".//h2[text() = 'Регистрация']");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Заполняем поле Имя")
    public void printName(String name) {
        driver.findElement(nameFieldOnRegisterPage).sendKeys(name);
    }

    @Step("Заполняем поле Email")
    public void printEmail(String email) {
        driver.findElement(emailFieldOnRegisterPage).sendKeys(email);
    }

    @Step("Заполняем поле Password")
    public void printPassword(String password) {
        driver.findElement(passwordFieldOnRegisterPage).sendKeys(password);
    }

    @Step("Проверка сообщения о некорректном пароле")
    public boolean isIncorrectPasswordMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordMessage)).isDisplayed();
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickRegisterButtonOnRegisterPage() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath("//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(registerButtonOnRegisterPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickEntryButtonOnRegisterPage() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath("//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(entryButtonOnRegisterPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Проверка перехода на страницу регистрации")
    public boolean isOnRegisterPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registerPageHeader)).isDisplayed();
    }
}
