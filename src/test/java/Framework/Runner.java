package Framework;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",
glue= {"StepDefinitions"},
monochrome = true,
plugin = {"pretty","html:target/HtmlReports/testResult.html",
		"json:target/JSONReports/testResult.html",
		"junit:target/XMLReports/testResult.html"},
tags = "@LoginFeature")
public class Runner {

}
