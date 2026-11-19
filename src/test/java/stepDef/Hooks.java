
package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import utilities.Driver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks
{

    private static final Logger logger =
            LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario)
    {
        logger.info("Starting scenario: {}", scenario.getName());
        Driver.getDriver();
        logger.info("Browser initialized");
    }

    @After
    public void tearDown(Scenario scenario)
    {
        logger.info("Scenario finished: {} | Status: {}",
                scenario.getName(), scenario.getStatus());

        try {
            if (scenario.isFailed())
            {
                logger.error("Scenario failed: {}", scenario.getName());

                WebDriver driver = Driver.getDriver();

                // Capture screenshot
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

                // Attach screenshot to Cucumber report
                scenario.attach(
                        screenshot,
                        "image/png",
                        "Failed Scenario Screenshot"
                );

                // Create screenshots directory
                Path screenshotDirectory =
                        Paths.get("target", "screenshots");

                Files.createDirectories(screenshotDirectory);

                // Generate unique screenshot filename
                String scenarioName = scenario.getName()
                        .replaceAll("[^a-zA-Z0-9.-]", "_");

                String fileName = scenarioName + "_"
                        + System.currentTimeMillis() + ".png";

                // Save screenshot
                Path screenshotPath =
                        screenshotDirectory.resolve(fileName);

                Files.write(screenshotPath, screenshot);

                System.out.println(
                        "Screenshot saved to: "
                                + screenshotPath.toAbsolutePath()
                );
            }

        } catch (IOException e)
        {
            System.err.println(
                    "Failed to save screenshot: " + e.getMessage()
            );

        } finally
        {
            Driver.closeDriver();
            logger.info("Browser closed");
        }
    }
}
