package test.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Page object for Login Tests
 */
public class LoginPageObject {


    private ChromeDriver driver;
    private WebDriverWait wait;


    public LoginPageObject(ChromeDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    /**
     * Opens the specified url
     * @param url - the url which will be used to test
     */
    public void open(String url) {
        driver.get(url);
    }

    /**
     * Identifies pseudo elements on page
     * @param myElem - the identified element
     */
    private void identifyPseudo(WebElement myElem) {
        driver.executeScript("return window.getComputedStyle(arguments[0], ':before').getPropertyValue('content');", myElem).toString();
    }

    /**
     * Fills in the login credentials and clicks on login button
     * @param username - the username
     * @param pwd - the password
     */
    public void fillInLoginForm(String username, String pwd) {

        wait.until(visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        wait.until(visibilityOfElementLocated(By.id("password"))).sendKeys(pwd);

        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button/i"));

        identifyPseudo(loginButton);

        loginButton.click();
    }

    /**
     * Gets the message shown on a pop-up alert and compares it with the given (expected) text
     * @param text - the expected message to be shown on page
     */
    public boolean checkOverlineCurrentLoginPage(String text) {

        WebElement overlineMessage = driver.findElement(By.id("flash"));
        identifyPseudo(overlineMessage);

        String messageText = overlineMessage.getText();
        if (messageText.contains(text)) {
            return true;
        }
        return false;
    }

    /**
     * Finds the logout button and clicks it
     */
    public void clickLogoutButton() {
        wait.until(visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/a/i"))).click();
    }

}
