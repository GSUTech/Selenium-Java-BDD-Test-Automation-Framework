
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

public class Hooks {

    @Before
    public void setUp() {
        Driver.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {

        try {
            if (scenario.isFailed()) {

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

        } catch (IOException e) {
            System.err.println(
                    "Failed to save screenshot: " + e.getMessage()
            );

        } finally {
            Driver.closeDriver();
        }
    }
}
