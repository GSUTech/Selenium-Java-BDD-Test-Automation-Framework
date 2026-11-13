
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import utilities.WaitUtils;



public class LoginPage
{

    private static final Logger logger =
            LoggerFactory.getLogger(LoginPage.class);

    private final WebDriver driver;


    // Locators
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By message = By.id("flash");

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    // Enter username
    public void enterUsername(String user)
    {
        logger.info("Entering username");
        WaitUtils.waitForVisibility(username).sendKeys(user);
    }

    // Enter password
    public void enterPassword(String pass)
    {
        logger.info("Entering password");
        WaitUtils.waitForVisibility(password).sendKeys(pass);
    }

    // Click Login button
    public void clickLogin()
    {
        logger.info("Clicking Login button");
        WaitUtils.waitForClickable(loginButton).click();
    }

    // Get success or error message
    public String getMessage()
    {
        logger.info("Reading login response message");
        return WaitUtils.waitForVisibility(message).getText();
    }
}
