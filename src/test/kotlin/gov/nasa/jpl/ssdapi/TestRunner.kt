package gov.nasa.jpl.ssdapi

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        features = ["src/test/resources/jpl/ssd-api.steps"],
        plugin = ["pretty", "html:target/cucumber"]
)
class TestRunner {

}