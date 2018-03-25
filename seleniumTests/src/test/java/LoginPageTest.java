package test.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import test.pagemodel.LoginPageObject;

import static org.junit.Assert.assertTrue;

public class LoginPageTest {

    private static final String BASE_URL = "http://the-internet.herokuapp.com/";
    private static final String LOGIN_URL = BASE_URL + "login";
    private static SetWebDriver setWebDriver = new SetWebDriver();
    private static ChromeDriver driver;
    private LoginPageObject loginObject;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        loginObject = new LoginPageObject(driver);

        setWebDriver.startWebDriver();

        loginObject.open(LOGIN_URL);
        driver.manage().window().maximize();
    }

    @After
    public void closeBrowser() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }

    @Test
    public void checkSuccessLogin() {
        loginObject.fillInLoginForm("tomsmith", "SuperSecretPassword!");
        assertTrue(loginObject.checkOverlineCurrentLoginPage("You logged into a secure area!"));
    }

    @Test
    public void checkFailedLoginIncorrectUsername() {

        loginObject.fillInLoginForm("incorrectUsername", "SuperSecretPassword!");
        assertTrue(loginObject.checkOverlineCurrentLoginPage("Your username is invalid!"));
    }

    @Test
    public void checkFailedLoginIncorrectPassword() {

        loginObject.fillInLoginForm("tomsmith", "");
        assertTrue(loginObject.checkOverlineCurrentLoginPage("Your password is invalid!"));
    }

    @Test
    public void checkLogout() {
        loginObject.fillInLoginForm("tomsmith", "SuperSecretPassword!");
        loginObject.clickLogoutButton();
        assertTrue(loginObject.checkOverlineCurrentLoginPage("You logged out of the secure area!"));
    }
}
