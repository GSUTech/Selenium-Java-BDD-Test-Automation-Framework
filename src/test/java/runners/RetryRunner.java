
package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerun.txt",
        glue = {"stepDef"},
        plugin =
                {
                "pretty",
                "html:target/cucumber-retry-report.html",
                "json:target/cucumber-retry.json"
        }
)
public class RetryRunner
{

}

