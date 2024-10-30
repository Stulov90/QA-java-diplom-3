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
import pom.RegisterPage;

import java.time.Duration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

public class RegisterTest {
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
    @DisplayName("Успешная регистрация")
    public void successRegisterTest() {
        user.setName(user.getName() + "q");
        user.setEmail("q" + user.getEmail());
        user.setPassword(user.getPassword() + "q");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.openMainPage();
        mainPage.waitCreateBurgerHeader();
        mainPage.clickEntryButton();
        assertTrue("Не удалось перейти на страницу авторизации", loginPage.isOnLoginPage());
        loginPage.clickRegisterButtonOnLoginPage();
        assertTrue("Не удалось перейти на страницу регистрации", registerPage.isOnRegisterPage());
        registerPage.printName(user.getName());
        registerPage.printEmail(user.getEmail());
        registerPage.printPassword(user.getPassword());
        registerPage.clickRegisterButtonOnRegisterPage();
        assertTrue("Не удалось перейти на страницу авторизации", loginPage.isOnLoginPage());
    }

    @Test
    @DisplayName("Регистрация c невалидным паролем")
    public void failedRegisterTest() {
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword("qwert");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.openMainPage();
        mainPage.waitCreateBurgerHeader();
        mainPage.clickEntryButton();
        assertTrue("Не удалось перейти на страницу авторизации", loginPage.isOnLoginPage());
        loginPage.clickRegisterButtonOnLoginPage();
        assertTrue("Не удалось перейти на страницу регистрации", registerPage.isOnRegisterPage());
        registerPage.printName(user.getName());
        registerPage.printEmail(user.getEmail());
        registerPage.printPassword(user.getPassword());
        registerPage.clickRegisterButtonOnRegisterPage();
        assertTrue("Сообщение о некорректном пароле не появилось", registerPage.isIncorrectPasswordMessage());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserSteps.deleteUser(accessToken);
        }
        driver.quit();
    }
}
