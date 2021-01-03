package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utilities.Base;
import utilities.ClassHelp;
import utilities.Data;

public class Hooks extends Base {

	Data data = new Data();

	@Before(order = 0)
	public void beforeScenarioStart() {
		System.out.println(">>>>>>>>>>>>>>>>>>> Start of Scenario! <<<<<<<<<<<<<<<<<<<<");
		Reporter.assignAuthor("Muhammad Rozi A.");
	}

	@Before(order = 1)
	public void beforeScenario() {
		data.setTakeScreenshot(ClassHelp.getEnv("takeScreenshot"));
	}

	@After(order = 0)
	public void afterScenario(Scenario scenario) throws InterruptedException {
		if (scenario.isFailed()) {
			System.out.println("running test failed :(");
			if (data.getTakeScreenshot().equalsIgnoreCase("yes")) {
				takeScreenshot(scenario);
			}
		}

		if (!scenario.isFailed()) {
			System.out.println("RUNNING TEST SUCCESS!");
		}

		if (data.getCloseBrowserEveryScenario().equalsIgnoreCase("yes")) {
			driver.close();
			System.out.println("Close Driver...");
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<< End of Scenario! >>>>>>>>>>>>>>>>>>>");
	}

	public void takeScreenshot(Scenario scenario) throws InterruptedException {
		String scenarioName = scenario.getName().replaceAll(" ", "_");
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm").format(new Date());
		String screenshotName = scenarioName + "_" + timeStamp.replace(":", "_").replace(".", "_");

		try {
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destinationPath = new File(System.getProperty("user.dir") + "/target/htmlReport/" + screenshotName);
			Files.copy(sourcePath, destinationPath);
			Reporter.addScreenCaptureFromPath(destinationPath.toString());
			System.out.println("Take screenshot success..");
		} catch (IOException e) {
			System.out.println("Exception while taking ScreenShot " + e.getMessage());
			e.printStackTrace();
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.out.println("Exception with driver : " + somePlatformsDontSupportScreenshots.getMessage());
		}
	}
}
