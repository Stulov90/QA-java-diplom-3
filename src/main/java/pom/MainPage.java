package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static api.Client.BASE_URL;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By createBurgerHeaderOnMainPage = By.xpath(".//h1[contains(@class, 'text_type_main-large') and text()='Соберите бургер']");
    private final By personalAccountButtonOnMainPage = By.xpath(".//p[text()='Личный Кабинет']");
    private final By entryButtonOnMainPage = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By createOrderButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Оформить заказ']");
    private final By bunButton = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    private final By sauceButton = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    private final By fillingButton = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");
    private final By selectionSection = By.cssSelector("div.tab_tab__1SPyG.tab_tab_type_current__2BEPc.pt-4.pr-10.pb-4.pl-10");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открытие главной страницы сайта")
    public void openMainPage() {
        driver.get(BASE_URL);
    }

    @Step("Ожидание загрузки элемента главной страницы сайта")
    public void waitCreateBurgerHeader() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createBurgerHeaderOnMainPage)).isDisplayed();
    }

    @Step("Нажатие на кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath(".//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(personalAccountButtonOnMainPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void clickEntryButton() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath(".//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(entryButtonOnMainPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на раздел 'Булки'")
    public void clickBunButton() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath(".//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(bunButton);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на раздел 'Соусы'")
    public void clickSauceButton() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath(".//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(sauceButton);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на раздел 'Начинки'")
    public void clickFillingButton() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath(".//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(fillingButton);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Проверка перехода на главную страницу после авторизации")
    public boolean isOnMainPageAfterLoginUser() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton)).isDisplayed();
    }

    @Step("Проверка выбранной секции в конструкторе")
    public String isCorrectSection() {
        return driver.findElement(selectionSection).getText();
    }
}
