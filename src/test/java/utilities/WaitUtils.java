
package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils
{

    private static final int DEFAULT_TIMEOUT = 10;

    private WaitUtils()
    {
        // Utility class
    }

    public static WebElement waitForVisibility(By locator)
    {
        WebDriverWait wait = new WebDriverWait(
                Driver.getDriver(),
                Duration.ofSeconds(DEFAULT_TIMEOUT)
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static WebElement waitForClickable(By locator)
    {
        WebDriverWait wait = new WebDriverWait(
                Driver.getDriver(),
                Duration.ofSeconds(DEFAULT_TIMEOUT)
        );

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static void waitForUrlContains(String expectedText)
    {
        WebDriverWait wait = new WebDriverWait(
                Driver.getDriver(),
                Duration.ofSeconds(DEFAULT_TIMEOUT)
        );

        wait.until(
                ExpectedConditions.urlContains(expectedText)
        );
    }
}
