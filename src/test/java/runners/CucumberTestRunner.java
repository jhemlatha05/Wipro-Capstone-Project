package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",   // path to your .feature files
        glue = {"com.capstone.sauce.steps", "com.capstone.sauce.hooks"},        // point to your step defs
        plugin = {"pretty", "summary",
                  "html:target/cucumber-report.html",
                  "json:target/cucumber-report.json"},
        monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
