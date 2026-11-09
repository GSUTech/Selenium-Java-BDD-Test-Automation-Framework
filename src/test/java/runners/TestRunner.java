
package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/features",

        glue = "stepDef",
        tags = "@regression",


        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber-results.xml"
        },

        monochrome = false,
        dryRun = false

)
public class TestRunner {
}
