
package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {

            String browser =
                    ConfigReader.getProperty("browser");

            if (browser == null || browser.isBlank()) {
                throw new IllegalArgumentException(
                        "Browser is missing in config.properties"
                );
            }

            switch (browser.trim().toLowerCase()) {

                case "chrome":
                    driver = new ChromeDriver();
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
        }

        return driver;
    }

    public static void closeDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
