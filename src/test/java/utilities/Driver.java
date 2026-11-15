
package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

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


            boolean headless = Boolean.parseBoolean(
                    ConfigReader.getProperty("headless")
            );

            logger.info("Headless mode enabled: {}", headless);

            switch (browser) {

                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();

                    if (headless)
                    {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--window-size=1920,1080");
                    }

                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();

                    if (headless) {
                        edgeOptions.addArguments("--headless=new");
                        edgeOptions.addArguments("--window-size=1920,1080");
                    }

                    driver = new EdgeDriver(edgeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    if (headless) {
                        firefoxOptions.addArguments("-headless");
                        firefoxOptions.addArguments("--width=1920");
                        firefoxOptions.addArguments("--height=1080");
                    }

                    driver = new FirefoxDriver(firefoxOptions);
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
