
package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver
{

    private static final Logger logger =
            LoggerFactory.getLogger(Driver.class);

    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private Driver()
    {
        // Prevent creating Driver objects
    }

    public static WebDriver getDriver()
    {

        if (driverPool.get() == null)
        {

            String browser = ConfigReader.getProperty("browser");

            if (browser == null || browser.isBlank())
            {
                browser = "chrome";
            }

            browser = browser.trim().toLowerCase();

            logger.info("Initializing browser: {}", browser);

            WebDriver driver;

            switch (browser)
            {
                case "chrome":
                    driver = new ChromeDriver();
                    break;

                case "edge":
                    driver = new EdgeDriver();
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unsupported browser: " + browser
                    );
            }

            driver.manage().window().maximize();
            driverPool.set(driver);

            logger.info("Browser initialized successfully: {}", browser);
        }

        return driverPool.get();
    }

    public static void closeDriver()
    {

        WebDriver driver = driverPool.get();

        if (driver != null)
        {
            try {
                logger.info("Closing browser");
                driver.quit();
            } finally
            {
                driverPool.remove();
            }
        }
    }
}
