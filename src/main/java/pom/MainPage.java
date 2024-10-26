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
    private final By bunButtonOnMainPage = By.xpath(".//span[text()='Булки']");
    private final By sauceButtonOnMainPage = By.xpath(".//span[text()='Соусы']");
    private final By fillingButtonOnMainPage = By.xpath(".//span[text()='Начинки']");
    private final By createOrderButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Оформить заказ']");
    private final By fillingOnConstructor = By.xpath(".//p[@class='BurgerIngredient_ingredient__text__yp3dH' and contains(text(), 'Мясо бессмертных моллюсков')]");
    private final By sauceOnConstructor = By.xpath(".//p[@class='BurgerIngredient_ingredient__text__yp3dH' and text()='Соус фирменный Space Sauce']");
    private final By bunOnConstructor = By.xpath(".//p[@class='BurgerIngredient_ingredient__text__yp3dH' and text()='Краторная булка N-200i']");

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
            WebElement overlayElement = driver.findElement(By.xpath("//*[text()='Modal_modal_overlay__x2ZCr']"));
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
            WebElement overlayElement = driver.findElement(By.xpath("//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(entryButtonOnMainPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на кнопку 'Булки'")
    public void clickBunButton() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath("//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(bunButtonOnMainPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на кнопку 'Соусы'")
    public void clickSauceButton() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath("//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(sauceButtonOnMainPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Нажатие на кнопку 'Начинки'")
    public void clickFillingButton() {
        try {
            WebElement overlayElement = driver.findElement(By.xpath("//*[text()='Modal_modal_overlay__x2ZCr']"));
            if (overlayElement.isDisplayed()) {
                overlayElement.click();
            }
        } catch (NoSuchElementException e) {
            // Модальное окно больше не перекрывает элемет. Можно продолжать проверку.
        }
        WebElement element = driver.findElement(fillingButtonOnMainPage);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @Step("Проверка перехода на главную страницу после авторизации")
    public boolean isOnMainPageAfterLoginUser() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton)).isDisplayed();
    }

    @Step("Проверка отображения начинок в конструкторе")
    public boolean isFillingsOnDisplay() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fillingOnConstructor)).isDisplayed();
    }

    @Step("Проверка отображения соусов в конструкторе")
    public boolean isSaucesOnDisplay() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sauceOnConstructor)).isDisplayed();
    }

    @Step("Проверка отображения булок в конструкторе")
    public boolean isBunsOnDisplay() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bunOnConstructor)).isDisplayed();
    }
}
