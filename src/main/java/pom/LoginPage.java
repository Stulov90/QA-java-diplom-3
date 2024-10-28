package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By emailFieldOnLoginPage = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='name']");
    private final By passwordFieldOnLoginPage = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");
    private final By entryButtonOnLoginPage = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']");
    private final By registerButtonOnLoginPage = By.xpath(".//*[text()='Зарегистрироваться']");
    private final By passwordRecoveryButtonOnLoginPage = By.xpath(".//*[text()='Восстановить пароль']");
    private final By loginPageHeader = By.xpath(".//h2[text() = 'Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickRegisterButtonOnLoginPage() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath(".//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(registerButtonOnLoginPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickEntryButtonOnLoginPage() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath(".//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(entryButtonOnLoginPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на кнопку 'Восстановить пароль'")
    public void clickPasswordRecoveryButtonOnLoginPage() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath(".//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(passwordRecoveryButtonOnLoginPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Заполняем поле Email")
    public void printEmail(String email) {
        driver.findElement(emailFieldOnLoginPage).sendKeys(email);
    }

    @Step("Заполняем поле Password")
    public void printPassword(String password) {
        driver.findElement(passwordFieldOnLoginPage).sendKeys(password);
    }

    @Step("Проверка перехода на страницу авторизации")
    public boolean isOnLoginPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageHeader)).isDisplayed();
    }
}
