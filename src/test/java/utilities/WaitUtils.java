
package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WaitUtils
{

    private static final Logger logger = LoggerFactory.getLogger(WaitUtils.class);

    private static final int DEFAULT_TIMEOUT = Integer.parseInt(ConfigReader.getProperty("explicitWait"));

    private WaitUtils()
    {
        // Prevent creating objects of this utility class
    }

    // Wait until an element is visible
    public static WebElement waitForVisibility(By locator)
    {

        logger.info("Waiting for element visibility: {}", locator);

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            logger.info("Element is visible: {}", locator);
            return element;

        }
        catch (TimeoutException e)
        {
            logger.error("Timed out waiting for element visibility: {}", locator);
            throw e;
        }
    }

    // Wait until an element is clickable
    public static WebElement waitForClickable(By locator)
    {

        logger.info("Waiting for element to be clickable: {}", locator);

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));

        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

            logger.info("Element is clickable: {}", locator);
            return element;

        } catch (TimeoutException e)
        {
            logger.error("Timed out waiting for clickable element: {}", locator);
            throw e;
        }
    }

    // Wait until the URL contains expected text
    public static void waitForUrlContains(String expectedText)
    {

        logger.info("Waiting for URL to contain: {}", expectedText);

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));

        try {
            wait.until(
                    ExpectedConditions.urlContains(expectedText)
            );

            logger.info("URL contains expected text: {}", expectedText);

        } catch (TimeoutException e)
        {
            logger.error("Timed out waiting for URL to contain: {}", expectedText);
            throw e;
        }
    }
}
