
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage
{

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton =
            By.cssSelector("button[type='submit']");
    private final By message = By.id("flash");

    // Constructor
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));
    }

    // Enter username
    public void enterUsername(String user)
    {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(username)
        );
        element.sendKeys(user);
    }

    // Enter password
    public void enterPassword(String pass)
    {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(password)
        );
        element.sendKeys(pass);
    }

    // Click Login button
    public void clickLogin()
    {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }

    // Get success or error message
    public String getMessage()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(message)
        ).getText();
    }
}
