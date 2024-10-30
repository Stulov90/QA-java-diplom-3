import api.User;
import api.UserSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pom.LoginPage;
import pom.MainPage;
import pom.ProfilePage;

import java.time.Duration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTest {

    private WebDriver driver;
    private User user;
    String accessToken;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        if ("firefox".equals(browser)) {
            startFirefox();
        } else {
            startChrome();
        }
        createUser();
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

    public void createUser() {
        user = UserSteps.createUserWithRandomData();
        Response response = UserSteps.createUser(user);
        accessToken = response.then().extract().path("accessToken");
        response.then().log().all().assertThat().statusCode(200)
                .and().body("success", equalTo(true))
                .and().body("accessToken", notNullValue())
                .and().body("refreshToken", notNullValue())
                .and().body("user.email", equalTo(user.getEmail()))
                .and().body("user.name", equalTo(user.getName()));
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void personalAccountEnterTest() {
        String email = user.getEmail();
        String password = user.getPassword();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.openMainPage();
        mainPage.waitCreateBurgerHeader();
        mainPage.clickEntryButton();
        assertTrue("Не удалось перейти на страницу авторизации", loginPage.isOnLoginPage());
        loginPage.printEmail(email);
        loginPage.printPassword(password);
        loginPage.clickEntryButtonOnLoginPage();
        assertTrue("Не удалось перейти на главную страницу сайта после авторизации", mainPage.isOnMainPageAfterLoginUser());
        mainPage.clickPersonalAccountButton();
        assertTrue("Не удалось перейти в личный кабинет", profilePage.isOnPersonalAccountPage());
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке 'Выход' в личном кабинете")
    public void personalAccountExitTest() {
        String email = user.getEmail();
        String password = user.getPassword();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.openMainPage();
        mainPage.waitCreateBurgerHeader();
        mainPage.clickEntryButton();
        assertTrue("Не удалось перейти на страницу авторизации", loginPage.isOnLoginPage());
        loginPage.printEmail(email);
        loginPage.printPassword(password);
        loginPage.clickEntryButtonOnLoginPage();
        assertTrue("Не удалось перейти на главную страницу сайта после авторизации", mainPage.isOnMainPageAfterLoginUser());
        mainPage.clickPersonalAccountButton();
        assertTrue("Не удалось перейти в личный кабинет", profilePage.isOnPersonalAccountPage());
        profilePage.clickExitButton();
        assertTrue("Не удалось перейти на страницу авторизации", loginPage.isOnLoginPage());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на 'Конструктор'")
    public void constructorButtonTest() {
        String email = user.getEmail();
        String password = user.getPassword();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.openMainPage();
        mainPage.waitCreateBurgerHeader();
        mainPage.clickEntryButton();
        assertTrue("Не удалось перейти на страницу авторизации", loginPage.isOnLoginPage());
        loginPage.printEmail(email);
        loginPage.printPassword(password);
        loginPage.clickEntryButtonOnLoginPage();
        assertTrue("Не удалось перейти на главную страницу сайта после авторизации", mainPage.isOnMainPageAfterLoginUser());
        mainPage.clickPersonalAccountButton();
        assertTrue("Не удалось перейти в личный кабинет", profilePage.isOnPersonalAccountPage());
        profilePage.clickConstructorButton();
        assertTrue("Не удалось перейти из личного кабинета в раздел 'Конструктор' по клику на кнопку 'Конструктор'", mainPage.isOnMainPageAfterLoginUser());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void logoStellarBurgersTest() {
        String email = user.getEmail();
        String password = user.getPassword();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.openMainPage();
        mainPage.waitCreateBurgerHeader();
        mainPage.clickEntryButton();
        assertTrue("Не удалось перейти на страницу авторизации", loginPage.isOnLoginPage());
        loginPage.printEmail(email);
        loginPage.printPassword(password);
        loginPage.clickEntryButtonOnLoginPage();
        assertTrue("Не удалось перейти на главную страницу сайта после авторизации", mainPage.isOnMainPageAfterLoginUser());
        mainPage.clickPersonalAccountButton();
        assertTrue("Не удалось перейти в личный кабинет", profilePage.isOnPersonalAccountPage());
        profilePage.clickLogoStellarBurgers();
        assertTrue("Не удалось перейти из личного кабинета в раздел 'Конструктор' по клику на кнопку 'Конструктор'", mainPage.isOnMainPageAfterLoginUser());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserSteps.deleteUser(accessToken);
        }
        driver.quit();
    }
}
