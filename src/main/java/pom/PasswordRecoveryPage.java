package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordRecoveryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By entryButtonOnPasswordRecoveryPage = By.xpath(".//a[text()='Войти']");
    private final By passwordRecoveryHeader = By.xpath(".//h2[text() = 'Восстановление пароля']");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickEntryButtonOnPasswordRecoveryPage() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath(".//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(entryButtonOnPasswordRecoveryPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Проверка перехода на страницу восстановления пароля")
    public boolean isOnPasswordRecoveryPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordRecoveryHeader)).isDisplayed();
    }
}
