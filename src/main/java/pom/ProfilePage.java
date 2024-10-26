package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By saveButton = By.xpath(".//button[text()='Сохранить']");
    private final By logoStellarBurgers = By.className("AppHeader_header__logo__2D0X2");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logoutButton = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажатие на кнопку 'Конструктор'")
    public void clickConstructorButton() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath("//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(constructorButton);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на логотип 'Stellar Burgers'")
    public void clickLogoStellarBurgers() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath("//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(logoStellarBurgers);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на кнопку 'Выход'")
    public void clickExitButton() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath("//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(logoutButton);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Проверка перехода на страницу восстановления пароля")
    public boolean isOnPersonalAccountPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton)).isDisplayed();
    }
}
