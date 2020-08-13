import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\QA\\estudos\\kafka-avro\\kafka-avro-course-master\\kafka-avro-v1\\src\\main\\resources\\features", tags = "@exemplo",
glue = "Steps", monochrome = true, dryRun = false)
public class Runner {
}