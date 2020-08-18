import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\main\\resources\\features", tags = "@exemplo",
glue = "Steps", monochrome = true, dryRun = false)
public class Runner {
}