
package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/features",

        glue = "stepDef",
        tags = "${cucumber.filter.tags}",


        plugin = {

                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber-results.xml",
                "rerun:target/rerun.txt"
        },

        monochrome = false,
        dryRun = false

)
public class TestRunner {
}
