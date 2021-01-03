package runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith (Cucumber.class)

@CucumberOptions (
	features 	= "src/test/java/features/",
	glue 		= {"stepDefinitions"},
	tags 		= {"~@Ignore"},
	plugin 		= {
		"com.cucumber.listener.ExtentCucumberFormatter:",
		"json:target/jsonReport/cucumber-json.json",
		"pretty:target/otherReport/cucumber-pretty.txt",
        "usage:target/otherReport/cucumber-usage.json",
        "html:target/otherReport/cucumber-html",
        "junit:target/otherReport/cucumber-results.xml"
	},
	dryRun 		= false,
	monochrome 	= true,
	strict		= true
)
public class TestRunner {
	@BeforeClass
    public static void setup() {
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("target/htmlReport/TestReport.html");
    }
	
    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig("extent-report.xml");
        Reporter.setSystemInfo("Author ", System.getProperty("user.name") + "-Rozi");
        Reporter.setSystemInfo("Time Zone ", System.getProperty("user.timezone"));
    }
}
