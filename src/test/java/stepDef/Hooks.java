
package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

public class Hooks {

    @After
    public void tearDown(Scenario scenario) {

        WebDriver driver = Driver.getDriver();

        try {

            if (scenario.isFailed()) {

                byte[] screenshot =
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES);

                scenario.attach(
                        screenshot,
                        "image/png",
                        "Failure Screenshot"
                );

                System.out.println(
                        "Screenshot attached for failed scenario: "
                                + scenario.getName()
                );
            }

        } finally {

            Driver.closeDriver();
        }
    }
}
