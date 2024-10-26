import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pom.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    private WebDriver driver;

    @Before
    public void initDriver() {
        String browser = System.getProperty("browser", "chrome");
        if ("firefox".equals(browser)) {
            startFirefox();
        } else {
            startChrome();
        }
    }

    public void startChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    public void startFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void fillingsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitCreateBurgerHeader();
        mainPage.clickFillingButton();
        assertTrue("Не удалось перейти к выбору начинок", mainPage.isFillingsOnDisplay());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void saucesTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitCreateBurgerHeader();
        mainPage.clickSauceButton();
        assertTrue("Не удалось перейти к выбору соусов", mainPage.isSaucesOnDisplay());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void bunsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.waitCreateBurgerHeader();
        mainPage.clickSauceButton();
        assertTrue("Не удалось перейти к выбору соусов", mainPage.isSaucesOnDisplay());
        mainPage.clickBunButton();
        assertTrue("Не удалось перейти к выбору булок", mainPage.isBunsOnDisplay());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
